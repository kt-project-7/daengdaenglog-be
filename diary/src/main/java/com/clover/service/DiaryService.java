package com.clover.service;

import com.clover.dto.request.CreateDiaryRequest;
import com.clover.dto.response.DiarySimpleListResponse;
import com.clover.dto.response.PetDiaryListResponse;
import com.clover.dto.response.DiarySimpleResponse;
import com.clover.dto.response.PetDiaryResponse;
import com.clover.repository.DiaryRepository;
import com.clover.service.client.PetClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final PetClient petClient;

    public PetDiaryListResponse getPetDiaryList(
            Long userId, int size
    ) {
        List<PetDiaryResponse> list = petClient.getPetIdList(userId).stream()
                .map(petInfo -> {
                    List<DiarySimpleResponse> diaryList = diaryRepository.getDiaryList(petInfo.petId(), 0, size);
                    return PetDiaryResponse.of(petInfo, diaryList);
                })
                .toList();

        return PetDiaryListResponse.from(list);
    }

    public DiarySimpleListResponse getDiaryListPaging(Long petId, int page, int size) {
        return DiarySimpleListResponse.from(diaryRepository.getDiaryList(petId, page, size));
    }

    @Transactional
    public void createDiary(CreateDiaryRequest request) {
        //TODO: request에 있는 petId가 유효한지 확인 로직 추가
        diaryRepository.save(request.toEntity());
    }
}