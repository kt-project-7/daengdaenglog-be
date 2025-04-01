package com.clover.service;

import com.clover.dto.response.SummaryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final SummaryService summaryService;

    @KafkaListener(topics = "summary-response", groupId = "consumer-group")
    public void consumeMessage(@Payload SummaryResponse message) {
        log.info("Consumed message: {}", message);

        summaryService.saveSummary(message);
    }
}