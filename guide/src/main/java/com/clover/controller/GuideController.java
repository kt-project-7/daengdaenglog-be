package com.clover.controller;

import com.clover.dto.ResponseTemplate;
import com.clover.dto.request.GuideGenerateRequest;
import com.clover.service.GuideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "GuideController", description = "Guide 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class GuideController {

    private final GuideService guideService;

    @Operation(summary = "가이드 생성", description = "가이드 생성 요청 시 사용")
    @PostMapping("/{petId}")
    public ResponseEntity<ResponseTemplate<?>> generateGuide(
            @PathVariable Long petId,
            @RequestBody GuideGenerateRequest guideGenerateRequest
    ) {
        guideService.guideInit(petId, guideGenerateRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(null));
    }

    //TODO: 생성된 가이드 상세 정보 조회 api - 생성된 가이드 정보 조회 요청 시 사용

    //TODO: 가이드 리스트 조회 api
}
