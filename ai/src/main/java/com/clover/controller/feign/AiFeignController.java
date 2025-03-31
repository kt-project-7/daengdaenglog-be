package com.clover.controller.feign;

import com.clover.dto.request.feign.FeignImageGenerateRequest;
import com.clover.dto.request.feign.FeignPetDiaryDetailListResponse;
import com.clover.service.AiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PetFeignController", description = "Pet Feign 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/feign")
@RestController
public class AiFeignController {

    private final AiService aiService;

    @Operation(summary = "AI 이미지 생성", description = "프론트 사용 X - 백엔드 통신")
    @PostMapping("/image")
    public String generateImage(
            @RequestBody FeignImageGenerateRequest prompt
    ) {
        return aiService.generateImage(prompt);
    }

    @Operation(summary = "AI 텍스트 생성", description = "프론트 사용 X - 백엔드 통신")
    @PostMapping("/text")
    public String generateText(
            @RequestBody String prompt
    ) {
        return aiService.generateText(prompt);
    }

    @Operation(summary = "PBTI 분석", description = "프론트 사용 X - 백엔드 통신")
    @PostMapping("/pbti")
    public String generatePbti(
            @RequestBody FeignPetDiaryDetailListResponse requestList
    ) {
        return aiService.generatePbti(requestList.diaryList());
    }
}
