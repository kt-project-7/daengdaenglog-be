package com.clover.service.client;

import com.clover.dto.response.feign.FeignPetInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pet-service", url = "http://pet-service:8080")
public interface PetClient {

    @GetMapping("/feign/pets/{petId}/info")
    FeignPetInfoResponse getPetInfo(@PathVariable Long petId);
}
