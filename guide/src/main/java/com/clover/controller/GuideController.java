package com.clover.controller;

import com.clover.dto.ResponseTemplate;
import com.clover.dto.request.GuideGenerateRequest;
import com.clover.dto.response.GuideDetailResponse;
import com.clover.dto.response.GuideSimpleListResponse;
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

    @Operation(summary = "가이드 리스트 조회", description = "가이드 리스트 조회 요청 시 사용")
    @GetMapping("/{petId}")
    public ResponseEntity<ResponseTemplate<?>> getGuideList(
            @PathVariable Long petId
    ) {
        GuideSimpleListResponse guideList = guideService.getGuideList(petId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(guideList));
    }

    @Operation(summary = "가이드 상세 조회", description = "가이드 상세 조회 요청 시 사용")
    @GetMapping("/list/{guideId}")
    public ResponseEntity<ResponseTemplate<?>> getGuideDetail(
            @PathVariable Long guideId
    ) {
        GuideDetailResponse guideDetail = guideService.getGuideDetail(guideId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(guideDetail));
    }

}
