package com.clover.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "TestController", description = "Test 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class TestController {

    @GetMapping("/test")
    public String feignTest() {
        return "test";
    }
}
