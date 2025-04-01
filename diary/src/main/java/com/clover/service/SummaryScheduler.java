package com.clover.service;

import com.clover.service.client.PetClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SummaryScheduler {

    private final SummaryService summaryService;
    private final PetClient petClient;

    @Scheduled(cron = "0 0 0 * * *")  // 매일 자정 실행
    public void runDailySummary() {
        List<Long> petId = petClient.getAllPetId();

        for (Long id : petId) {
            summaryService.generateSummary(id);
        }
    }
}
