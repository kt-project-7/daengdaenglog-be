package com.clover.service;

import com.clover.domain.Diary;
import com.clover.domain.ScheduleTime;
import com.clover.dto.request.CreateDiaryRequest;
import com.clover.dto.request.UpdateDiaryRequest;
import com.clover.dto.request.feign.FeignImageGenScheduleRequest;
import com.clover.dto.request.feign.FeignImageGenerateRequest;
import com.clover.dto.response.*;
import com.clover.dto.response.feign.FeignPetDiaryDetailListResponse;
import com.clover.dto.response.feign.FeignPetDiaryDetailResponse;
import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.exception.DiaryNotFoundException;
import com.clover.exception.PetIdNotMatchException;
import com.clover.exception.errorcode.DiaryErrorCode;
import com.clover.repository.DiaryRepository;
import com.clover.repository.ScheduleTimeRepository;
import com.clover.service.client.AiClient;
import com.clover.service.client.PetClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final ScheduleTimeRepository scheduleTimeRepository;
    private final PetClient petClient;
    private final AiClient aiClient;

    /**
     * 펫 다이어리 리스트 조회
     */
    public PetDiaryListResponse getPetDiaryList(
            Long userId
    ) {
        List<PetDiaryResponse> list = petClient.getPetIdList(userId).stream()
                .map(petInfo -> {
                    List<DiarySimpleResponse> diaryList = diaryRepository.getDiaryList(petInfo.petId());
                    return PetDiaryResponse.of(petInfo, diaryList);
                })
                .toList();

        return PetDiaryListResponse.from(list);
    }

    /**
     * 펫 다이어리 상세 조회
     */
    public DiaryDetailResponse getDiaryDetailInfo(
            Long userId, Long diaryId
    ) {
        Diary diary = diaryRepository.findByIdFetch(diaryId)
                .orElseThrow(() -> new DiaryNotFoundException(DiaryErrorCode.DIARY_NOT_FOUND));

        validateIsPetOwner(userId, diary.getPetId());

        return DiaryDetailResponse.from(diary);
    }

    /**
     * 펫 다이어리 목록 상세 조회
     */
    public FeignPetDiaryDetailListResponse getDiaryDetailList(
            Long petId
    ) {
        List<FeignPetDiaryDetailResponse> list = diaryRepository.findAllByPetId(petId).stream()
                .map(FeignPetDiaryDetailResponse::from)
                .toList();

        return FeignPetDiaryDetailListResponse.from(list);
    }

    /**
     * 오늘 펫 다이어리 작성 여부 확인
     */
    public TodayDiaryResponse validateTodayDiary(
            Long userId, Long petId
    ) {
        validateIsPetOwner(userId, petId);

        return diaryRepository.findTodayDiaryId(petId)
                .map(diaryId -> TodayDiaryResponse.of(true, diaryId))
                .orElseGet(() -> TodayDiaryResponse.of(false, 0L));
    }

    /**
     * 펫 다이어리 수정
     */
    @Transactional
    public void updateDiary(
            Long userId, UpdateDiaryRequest request, MultipartFile file
    ) {
        Diary diary = diaryRepository.findById(request.diaryId())
                .orElseThrow(() -> new DiaryNotFoundException(DiaryErrorCode.DIARY_NOT_FOUND));

        validateIsPetOwner(userId, diary.getPetId());

        //TODO: file이 있으면 이미지 저장 로직 추가
        diary.updateDiary(request, null);

        request.diaryScheduleRequestList().forEach(schedule -> {
            ScheduleTime entity = schedule.toEntity();
            entity.updateDiary(diary);

            scheduleTimeRepository.save(entity);
        });
    }

    /**
     * ai 이미지 생성
     */
    @Transactional
    public String generateAiImage(
            Long userId, Long diaryId
    ) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new DiaryNotFoundException(DiaryErrorCode.DIARY_NOT_FOUND));

        validateIsPetOwner(userId, diary.getPetId());

        FeignPetInfoResponse petInfo = petClient.getPetInfo(diary.getPetId());

        List<FeignImageGenScheduleRequest> scheduleList = diary.getScheduleTimeList().stream()
                .map(FeignImageGenScheduleRequest::from)
                .toList();

        String imageUrl = aiClient.generateImage(FeignImageGenerateRequest.of(petInfo, diary, scheduleList));
        diary.updateGeneratedImageUri(imageUrl);

        return imageUrl;
    }

    /**
     * 펫 다이어리 작성
     */
    @Transactional
    public void createDiary(
            CreateDiaryRequest request, MultipartFile file, Long userId
    ) {
        validateIsPetOwner(userId, request.petId());

        //TODO: 이미지 저장 로직 추가

        diaryRepository.save(request.toEntity());
    }

    private void validateIsPetOwner(
            Long userId, Long petId
    ) {
        if (!petClient.validatePetId(petId, userId)) {
            throw new PetIdNotMatchException(DiaryErrorCode.PET_ID_NOT_MATCH);
        }
    }
}