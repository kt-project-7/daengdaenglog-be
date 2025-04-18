package com.clover.util;

import com.clover.dto.request.GuideInitRequest;
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

    @KafkaListener(topics = "summary-request", groupId = "ai-service")
    public void generateSummary(@Payload SummaryRequest message) {
        log.info("Consumed message: {}", message);

        aiService.generateSummary(message);
    }

    @KafkaListener(topics = "init-guide", groupId = "ai-service")
    public void initGuide(@Payload GuideInitRequest request) {
        log.info("Consumed message: {}", request);

        aiService.initGuide(request);
    }
}