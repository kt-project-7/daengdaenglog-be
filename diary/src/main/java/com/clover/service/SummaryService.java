package com.clover.service;

import com.clover.domain.SummaryData;
import com.clover.dto.request.SummaryDataRequest;
import com.clover.dto.request.SummaryRequest;
import com.clover.dto.response.SummaryResponse;
import com.clover.repository.DiaryRepository;
import com.clover.repository.SummaryDataRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class SummaryService {

    private final KafkaProducer kafkaProducer;
    private final DiaryRepository diaryRepository;
    private final SummaryDataRepository summaryDataRepository;

    public void generateSummary(Long petId) {

        // 이번 달의 첫 번째 날과 마지막 날 계산
        LocalDateTime startDate = LocalDate.now().withDayOfMonth(1).atStartOfDay();
        LocalDateTime endDate = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()).atTime(23, 59, 59);

        List<SummaryDataRequest> dataRequests = diaryRepository.findAllByPetIdAndCreatedDateBetween(petId, startDate, endDate).stream()
                .map(SummaryDataRequest::from)
                .toList();

        SummaryRequest summaryResponse = SummaryRequest.of(petId, (long) startDate.getYear(), (long) startDate.getMonthValue(), dataRequests);

        kafkaProducer.send("summary-request", summaryResponse);

    }

    @Transactional
    public void saveSummary(SummaryResponse message) {
        Optional<SummaryData> summaryData = summaryDataRepository.findByPetIdAndYearAndMonth(
            message.petId(), message.year(), message.month());

        if (summaryData.isEmpty()) {
            summaryDataRepository.save(message.toEntity());
        } else {
            SummaryData existingData = summaryData.get();
            if (!existingData.getDescription().equals(message.summary())) {  // 중복 방지
                existingData.setDescription(message.summary());
                summaryDataRepository.save(existingData);
            }
        }
    }
}
