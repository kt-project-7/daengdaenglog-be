package com.clover.controller;

import com.clover.service.AiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AiController", description = "Ai 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class AiController {

    private final AiService aiService;

    //TODO: 추억 그림 생성 api - ai를 이용한 추억 그림 생성 및 memory 테이블에 추가
    @GetMapping("/image-test")
    public String test() {
        return aiService.generateImage("asd");
    }

    @GetMapping("/chat-test")
    public String chatTest(@RequestParam String userInput) {
        return aiService.getChatResponse(userInput);
    }
}
