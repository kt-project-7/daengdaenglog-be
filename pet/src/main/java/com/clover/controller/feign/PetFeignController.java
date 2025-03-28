package com.clover.controller.feign;

import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.service.FeignPetService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "PetFeignController", description = "Pet Feign 관련 API")
@Slf4j
@RequiredArgsConstructor
@RequestMapping(path = "/feign")
@RestController
public class PetFeignController {

    private final FeignPetService feignPetService;

    @GetMapping("/pet-list")
    public List<FeignPetInfoResponse> getPetIdList(Long userId) {
        return feignPetService.getPetIdList(userId);
    }
}
