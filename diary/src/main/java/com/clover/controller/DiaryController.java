package com.clover.controller;

import com.clover.dto.ResponseTemplate;
import com.clover.dto.request.CreateDiaryRequest;
import com.clover.dto.response.DiarySimpleListResponse;
import com.clover.dto.response.PetDiaryListResponse;
import com.clover.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "DiaryController", description = "Diary 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(summary = "펫 다이어리 리스트 조회", description = "처음 펫 다이어리 리스트 조회")
    @GetMapping
    public ResponseEntity<ResponseTemplate<?>> getPetDiaryList(@RequestParam Long userId, @RequestParam int size) {
        PetDiaryListResponse petDiaryList = diaryService.getPetDiaryList(userId, size);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(petDiaryList));
    }

    @Operation(summary = "펫 다이어리 페이징 조회", description = "각각의 펫 다이어리 페이징 조회")
    @GetMapping("/{petId}")
    public ResponseEntity<ResponseTemplate<?>> getPetDiaryListPaging(@PathVariable Long petId, @RequestParam int page, @RequestParam int size) {
        DiarySimpleListResponse diaryListPaging = diaryService.getDiaryListPaging(petId, page, size);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(diaryListPaging));
    }

    //TODO: 여기 EmotionType 등 다 적어주기
    @Operation(summary = "펫 다이어리 생성", description = "펫 다이어리 생성")
    @PostMapping
    public ResponseEntity<ResponseTemplate<?>> createDiary(@RequestBody CreateDiaryRequest request) {
        diaryService.createDiary(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.EMPTY_RESPONSE);
    }
}
