package com.clover.controller;

import com.clover.dto.ResponseTemplate;
import com.clover.dto.response.PetSimpleInfoListResponse;
import com.clover.service.PetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pet Controller", description = "Pet 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping
@RestController
public class PetController {

    private final PetService petService;

    //TODO: pbti 생성 api - 펫 유형정보 분석 요청 시 사용

    @Operation(summary = "펫 목록 조회", description = "펫 목록 조회(펫 리스트만 조회)")
    @GetMapping
    public ResponseEntity<ResponseTemplate<?>> getPetList(
            HttpServletRequest request
    ) {
        Long userId = Long.parseLong(request.getHeader("User-Id"));
        PetSimpleInfoListResponse response = petService.getPetList(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseTemplate.from(response));
    }
}
