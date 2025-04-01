package com.clover.util;

import com.clover.domain.GuideInitOutbox;
import com.clover.dto.response.GuideResponse;
import com.clover.repository.GuideInitOutboxRepository;
import com.clover.repository.GuideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Component
public class KafkaConsumer {

    private final GuideRepository guideRepository;
    private final GuideInitOutboxRepository guideInitOutboxRepository;

    @KafkaListener(topics = "guide-init-response", groupId = "guide-group")
    public void generateSummary(@Payload GuideResponse message) {
        log.info("Consumed message: {}", message);

        guideRepository.findById(message.guideId())
                .ifPresent(guide ->  guide.updateGuide(message.description()));

        guideInitOutboxRepository.findByGuideId(message.guideId())
                .ifPresent(GuideInitOutbox::updateStatus);
    }
}