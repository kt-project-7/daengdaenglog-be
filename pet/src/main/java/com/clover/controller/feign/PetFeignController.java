package com.clover.controller.feign;

import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.service.FeignPetService;
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

    @GetMapping("/pets")
    public List<FeignPetInfoResponse> getPetIdList(@RequestParam Long userId) {
        return feignPetService.getPetIdList(userId);
    }

    @GetMapping("/pets/{petId}")
    public boolean validatePetId(@PathVariable Long petId, @RequestParam Long userId) {
        return feignPetService.validatePetId(petId, userId);
    }
}
