package com.clover.controller;

import com.clover.dto.ResponseTemplate;
import com.clover.dto.request.CreateDiaryRequest;
import com.clover.dto.request.UpdateDiaryRequest;
import com.clover.dto.response.DiaryDetailResponse;
import com.clover.dto.response.PetDiaryListResponse;
import com.clover.dto.response.TodayDiaryResponse;
import com.clover.service.DiaryService;
import com.clover.service.SummaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "DiaryController", description = "Diary 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class DiaryController {

    private final DiaryService diaryService;
    private final SummaryService summaryService;

    @Operation(summary = "펫 다이어리 리스트 조회", description = "처음 펫 다이어리 리스트 조회")
    @GetMapping
    public ResponseEntity<ResponseTemplate<?>> getPetDiaryList(
            HttpServletRequest request
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        PetDiaryListResponse petDiaryList = diaryService.getPetDiaryList(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(petDiaryList));
    }

    @Operation(summary = "펫 다이어리 상세 조회", description = "memoryId가 0이면 관련 이미지 없는 것, 자기 자신의 펫 다이어리만 조회 가능")
    @GetMapping("/{diaryId}")
    public ResponseEntity<ResponseTemplate<?>> getDiaryDetailInfo(
            HttpServletRequest request,
            @PathVariable Long diaryId
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        DiaryDetailResponse diaryDetailInfo = diaryService.getDiaryDetailInfo(userId, diaryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(diaryDetailInfo));
    }

    @Operation(summary = "오늘 펫 다이어리 작성 여부 확인", description = "오늘 펫 다이어리 작성 여부 확인 - 작성했으면 diaryId 반환 -> 상세 조회 후 수정 가능<br>" +
            "isWrite가 true이면 diaryId가 반환되고, false이면 0이 반환됨")
    @GetMapping(value = "/today")
    public ResponseEntity<ResponseTemplate<?>> validateTodayDiary(
            HttpServletRequest request,
            @RequestParam Long petId
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        TodayDiaryResponse response = diaryService.validateTodayDiary(userId, petId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }

    @Operation(summary = "펫 다이어리 수정", description = "자기 자신의 펫 다이어리만 수정 가능")
    @PutMapping(value = "/{diaryId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseTemplate<?>> updateDiary(
            HttpServletRequest request,
            @PathVariable Long diaryId,
            @RequestPart UpdateDiaryRequest updateDiaryRequest,
            @RequestPart(required = false) MultipartFile file
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        diaryService.updateDiary(userId, diaryId, updateDiaryRequest, file);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @Operation(summary = "추억 그림 생성", description = "추억 그림 생성<br> AI를 이용한 추억 그림 생성")
    @GetMapping("/{diaryId}/image")
    public ResponseEntity<ResponseTemplate<?>> generateImage(
            HttpServletRequest request,
            @PathVariable Long diaryId
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        String imageUrl = diaryService.generateAiImage(userId, diaryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(imageUrl));
    }

    @Operation(summary = "펫 다이어리 생성", description = "펫 다이어리 생성<br>" +
            "EmotionType: HAPPY, SAD, ANGRY, SURPRISED, HUNGRY, SICK, LOVE, SLEEPY<br>" +
            "WeatherType: SUNNY, CLOUDY, RAINY, SNOWY, THUNDER, HAILSTONE, FOG, YELLOW_DUST<br>" +
            "ScheduleType: WALK, FEED")
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResponseTemplate<?>> createDiary(
            HttpServletRequest request,
            @RequestPart CreateDiaryRequest createDiaryRequest,
            @RequestPart(required = false) MultipartFile file
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        diaryService.createDiary(createDiaryRequest, file, userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @Operation(summary = "펫 다이어리 삭제", description = "자기 자신의 펫 다이어리만 삭제 가능")
    @DeleteMapping("/{diaryId}")
    public ResponseEntity<ResponseTemplate<?>> deleteDiary(
            HttpServletRequest request,
            @PathVariable Long diaryId
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        diaryService.deleteDiary(userId, diaryId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseTemplate<?>> test(
            @RequestParam Long petId
    ) {
        summaryService.generateSummary(petId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }
}
