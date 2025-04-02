package com.clover.service.handler;

import com.clover.dto.request.GuideInitRequest;
import com.clover.repository.GuideInitOutboxRepository;
import com.clover.service.KafkaProducer;
import com.clover.service.event.GuideInitEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Component
public class GuideInitEventHandler {

    private final GuideInitOutboxRepository guideInitOutboxRepository;
    private final KafkaProducer kafkaProducer;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleCompletion(GuideInitEvent event) {
        log.info("guide outbox save: {}", event);

        guideInitOutboxRepository.save(event.toOutbox());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(GuideInitEvent event) {
        log.info("guide outbox send: {}", event);

        kafkaProducer.send("init-guide", new GuideInitRequest(event.petId(), event.guideId()));
        guideInitOutboxRepository.findByGuideId(event.guideId())
                .ifPresent(guideInitOutboxRepository::delete);
    }
}
