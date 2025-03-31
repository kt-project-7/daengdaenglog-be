package com.clover.service.client;

import com.clover.dto.response.feign.FeignPetDiaryDetailListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "ai-service", url = "http://ai-service:8080")
public interface AiClient {

    @PostMapping("/feign/pbti")
    String generatePbti(@RequestBody FeignPetDiaryDetailListResponse request);
}
