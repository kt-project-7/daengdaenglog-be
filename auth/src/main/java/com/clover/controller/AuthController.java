package com.clover.controller;

import com.clover.dto.ResponseTemplate;
import com.clover.dto.request.SignInRequest;
import com.clover.dto.response.AuthResponse;
import com.clover.dto.response.SignInResponse;
import com.clover.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "Auth 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "테스트용 토큰발급", description = "테스트용 토큰발급")
    @GetMapping("/test/{userId}")
    public String testToken(@PathVariable Long userId) {
        return authService.getTestToken(userId);
    }

    @Operation(summary = "로그인 진행", description = "access token은 header에 Authorization: Bearer 로, refresh token은 쿠키로 전달")
    @PostMapping("/sign-in")
    public ResponseEntity<ResponseTemplate<?>> signIn(
            @RequestBody SignInRequest signInRequest, HttpServletResponse response
    ) {
        SignInResponse selfSignInResponse = authService.signIn(response, signInRequest);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(selfSignInResponse));
    }

    @Operation(summary = "토큰 유효성 검사", description = "토큰 유효성 검사")
    @GetMapping("/validate-token")
    public AuthResponse validateToken(@RequestHeader("Authorization") String token) {
        AuthResponse userId = authService.validateToken(token);

        log.info("userId: {}", userId.userId());

        return userId;
    }
}
