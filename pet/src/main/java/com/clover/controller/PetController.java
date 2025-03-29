package com.clover.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pet=Controller", description = "Pet 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class PetController {

    //TODO: pbti 생성 api - 펫 유형정보 분석 요청 시 사용

    //TODO: 펫 목록 조회 api

    //TODO: 펫 상세 정보 조회 api - 펫 상세 정보 조회 요청 시 사용
}
