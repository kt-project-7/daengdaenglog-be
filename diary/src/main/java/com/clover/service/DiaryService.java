package com.clover.service;

import com.clover.domain.Diary;
import com.clover.domain.Memory;
import com.clover.dto.request.CreateDiaryRequest;
import com.clover.dto.response.*;
import com.clover.exception.DiaryNotFoundException;
import com.clover.exception.PetIdNotMatchException;
import com.clover.exception.errorcode.DiaryErrorCode;
import com.clover.repository.DiaryRepository;
import com.clover.repository.MemoryRepository;
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

    private final MemoryRepository memoryRepository;
    private final DiaryRepository diaryRepository;
    private final PetClient petClient;

    /**
     * 펫 다이어리 리스트 조회
     */
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

    /**
     * 펫 다이어리 페이징 조회
     */
    public DiarySimpleListResponse getDiaryListPaging(Long petId, int page, int size) {
        return DiarySimpleListResponse.from(diaryRepository.getDiaryList(petId, page, size));
    }

    /**
     * 펫 다이어리 상세 조회
     */
    public DiaryDetailResponse getDiaryDetailInfo(Long userId, Long diaryId) {
        Diary diary = diaryRepository.findByIdFetch(diaryId)
                .orElseThrow(() -> new DiaryNotFoundException(DiaryErrorCode.DIARY_NOT_FOUND));

        validateIsPetOwner(userId, diary.getPetId());

        Memory memory = memoryRepository.findByDiaryId(diaryId).orElse(null);

        return DiaryDetailResponse.from(diary, memory);
    }

    /**
     * 오늘 펫 다이어리 작성 여부 확인
     */
    public TodayDiaryResponse validateTodayDiary(Long userId, Long petId) {
        validateIsPetOwner(userId, petId);

        return diaryRepository.findTodayDiaryId(petId)
                .map(diaryId -> TodayDiaryResponse.of(true, diaryId))
                .orElseGet(() -> TodayDiaryResponse.of(false, 0L));
    }

    /**
     * 펫 다이어리 작성
     */
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