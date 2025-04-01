package com.clover.util;

import com.clover.dto.request.SummaryRequest;
import com.clover.service.AiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final AiService aiService;

    @KafkaListener(topics = "summary-request", groupId = "summary-group")
    public void generateSummary(@Payload SummaryRequest message) {
        log.info("Consumed message: {}", message);

        aiService.generateSummary(message);
    }
}