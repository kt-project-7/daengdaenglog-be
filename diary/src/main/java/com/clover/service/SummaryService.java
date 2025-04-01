package com.clover.service;

import com.clover.dto.request.SummaryDataRequest;
import com.clover.dto.request.SummaryRequest;
import com.clover.repository.DiaryRepository;
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
}
