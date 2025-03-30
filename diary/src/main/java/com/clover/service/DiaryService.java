package com.clover.service;

import com.clover.dto.request.CreateDiaryRequest;
import com.clover.dto.response.*;
import com.clover.exception.PetIdNotMatchException;
import com.clover.exception.errorcode.DiaryErrorCode;
import com.clover.repository.DiaryRepository;
import com.clover.service.client.PetClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

    public TodayDiaryResponse validateTodayDiary(Long userId, Long petId) {
        validateIsPetOwner(userId, petId);

        return diaryRepository.findTodayDiaryId(petId)
                .map(diaryId -> TodayDiaryResponse.of(true, diaryId))
                .orElseGet(() -> TodayDiaryResponse.of(false, 0L));
    }

    @Transactional
    public void createDiary(CreateDiaryRequest request, MultipartFile file, Long userId) {
        validateIsPetOwner(userId, request.petId());

        //TODO: 이미지 저장 로직 추가 - memory 테이블

        diaryRepository.save(request.toEntity());
    }

    private void validateIsPetOwner(Long userId, Long petId) {
        if (!petClient.validatePetId(petId, userId)) {
            throw new PetIdNotMatchException(DiaryErrorCode.PET_ID_NOT_MATCH);
        }
    }
}