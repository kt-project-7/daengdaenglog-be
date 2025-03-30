package com.clover.controller;

import com.clover.dto.ResponseTemplate;
import com.clover.dto.request.CreateDiaryRequest;
import com.clover.dto.response.DiarySimpleListResponse;
import com.clover.dto.response.PetDiaryListResponse;
import com.clover.dto.response.TodayDiaryResponse;
import com.clover.service.DiaryService;
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

    @Operation(summary = "펫 다이어리 리스트 조회", description = "처음 펫 다이어리 리스트 조회")
    @GetMapping
    public ResponseEntity<ResponseTemplate<?>> getPetDiaryList(
            HttpServletRequest request,
            @RequestParam int size
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));

        log.info("userId: {}", userId);

        PetDiaryListResponse petDiaryList = diaryService.getPetDiaryList(userId, size);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(petDiaryList));
    }

    @Operation(summary = "펫 다이어리 페이징 조회", description = "각각의 펫 다이어리 페이징 조회")
    @GetMapping("/{petId}")
    public ResponseEntity<ResponseTemplate<?>> getPetDiaryListPaging(
            @PathVariable Long petId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        DiarySimpleListResponse diaryListPaging = diaryService.getDiaryListPaging(petId, page, size);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(diaryListPaging));
    }

    //TODO: 펫 다이어리 상세 조회(날짜 및 아이디로 조회) - 펫 다이어리는 연결된 추억 그림도 함께 주기 + 밥, 산책 시간도 주기

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

    //TODO: 관찰 일기 수정 api - 밥, 산책 시간도 추가 및 수정 삭제 가능

    //TODO: 추억 그림 생성 api - ai를 이용한 추억 그림 생성 및 memory 테이블에 추가

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
}
