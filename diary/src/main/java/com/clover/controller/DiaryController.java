package com.clover.controller;

import com.clover.dto.response.PetDiaryListResponse;
import com.clover.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "DiaryController", description = "Diary 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/diary")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(summary = "펫 다이어리 리스트 조회", description = "처음 펫 다이어리 리스트 조회")
    @GetMapping("/list")
    public PetDiaryListResponse getPetDiaryList(Long userId, int size) {
        return diaryService.getPetDiaryList(userId, size);
    }

    //TODO: 각각의 펫 다이어리 페이징 API 구현
}
