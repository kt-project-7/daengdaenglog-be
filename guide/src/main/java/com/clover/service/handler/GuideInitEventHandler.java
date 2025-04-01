package com.clover.service.handler;

import com.clover.repository.GuideInitOutboxRepository;
import com.clover.service.KafkaProducer;
import com.clover.service.event.GuideInitEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Transactional
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

        //TODO: 추상화된 데이터를 보낼 수 있게 변경
        kafkaProducer.send("init-guide", event);
        guideInitOutboxRepository.deleteById(event.guideId());
    }
}
