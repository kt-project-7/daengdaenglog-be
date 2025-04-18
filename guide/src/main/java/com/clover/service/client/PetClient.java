package com.clover.service.client;

import com.clover.dto.response.feign.FeignPetInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "pet-service", url = "http://pet-service:8080")
public interface PetClient {

    @GetMapping("/feign/pets")
    List<FeignPetInfoResponse> getPetInfoList(@RequestParam Long userId);
}
