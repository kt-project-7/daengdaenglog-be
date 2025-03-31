package com.clover.service.client;

import com.clover.dto.response.feign.FeignPetDiaryDetailListResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "diary-service", url = "http://diary-service:8080")
public interface DiaryClient {

    @GetMapping("/feign/diary")
    FeignPetDiaryDetailListResponse getDiary(@RequestParam Long petId);
}
