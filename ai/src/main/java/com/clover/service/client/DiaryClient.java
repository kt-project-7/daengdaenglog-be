package com.clover.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@FeignClient(name = "diary-service", url = "http://diary-service:8080")
public interface DiaryClient {

    @GetMapping("/feign/diary/summary")
    List<String> getDiary(@RequestParam Long petId);
}
