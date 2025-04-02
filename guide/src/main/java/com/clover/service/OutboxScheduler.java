package com.clover.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxScheduler {

    private final GuideService guideService;

    @Scheduled(cron = "0 */5 * * * *")  // 5분에 한 번씩 실행
    public void runDailySummary() {
        log.info("Running DailySummary Scheduler");
        guideService.regenerateGuide();
    }
}
