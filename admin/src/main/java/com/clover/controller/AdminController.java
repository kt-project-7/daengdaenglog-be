package com.clover.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AdminController", description = "Admin 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class AdminController {

    //TODO: 유저 생성 api - 유저 생성 요청 시 사용 - 비동기로 auth-service에 유저 생성 요청


    //TODO: 펫 정보 생성 api - 펫 정보 생성 요청 시 사용 - 비동기로 pet-service에 펫 정보 생성 요청
}
