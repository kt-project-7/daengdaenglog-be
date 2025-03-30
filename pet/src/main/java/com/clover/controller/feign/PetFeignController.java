package com.clover.controller.feign;

import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.service.FeignPetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "PetFeignController", description = "Pet Feign 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/feign")
@RestController
public class PetFeignController {

    private final FeignPetService feignPetService;

    @Operation(summary = "펫 목록 조회", description = "프론트 사용 X - 백엔드 통신")
    @GetMapping("/pets")
    public List<FeignPetInfoResponse> getPetIdList(@RequestParam Long userId) {
        return feignPetService.getPetIdList(userId);
    }

    @Operation(summary = "펫 주인 검증", description = "프론트 사용 X - 백엔드 통신")
    @GetMapping("/pets/{petId}")
    public boolean validatePetId(@PathVariable Long petId, @RequestParam Long userId) {
        return feignPetService.validatePetId(petId, userId);
    }

    //TODO: 보험 청구 내역 요청 api - 보험 청구 내역 요청 시 사용(테이블 만들어야 함)
}
