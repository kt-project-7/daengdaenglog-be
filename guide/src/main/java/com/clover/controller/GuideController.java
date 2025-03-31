package com.clover.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "GuideController", description = "Guide 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class GuideController {

    //TODO: 가이드 생성 api - 최근 관찰 일지 및 펫 정보, 보험 청구 내역 가져와서 요약 정보 생성(ai 활용) - 가이드는 하나만 존재인지 물어보기

    //TODO: 생성된 가이드 정보 조회 api - 생성된 가이드 정보 조회 요청 시 사용
}
