package com.clover.controller.feign;

import com.clover.dto.response.feign.FeignPetDiaryDetailListResponse;
import com.clover.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "DiaryController", description = "Diary 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/feign")
@RestController
public class DiaryFeignController {

    private final DiaryService diaryService;

    @Operation(summary = "펫 다이어리 리스트 조회", description = "처음 펫 다이어리 리스트 조회")
    @GetMapping("/diary")
    public FeignPetDiaryDetailListResponse getPetDiaryList(
            @RequestParam Long petId
    ) {

        log.info("getPetDiaryList - petId: {}", petId);
        FeignPetDiaryDetailListResponse petDiaryList = diaryService.getDiaryDetailList(petId);

        log.info("getPetDiaryList - petDiaryList: {}", petDiaryList);

        return petDiaryList;
    }

    @Operation(summary = "요약된 펫 다이어리 리스트 조회", description = "요약된 펫 다이어리 리스트 조회")
    @GetMapping("/diary/summary")
    public List<String> getSummaryPetDiaryList(
            @RequestParam Long petId
    ) {

        return diaryService.getSummaryDiaryList(petId);
    }
}
