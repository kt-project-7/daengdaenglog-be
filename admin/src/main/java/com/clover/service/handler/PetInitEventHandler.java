package com.clover.service.handler;

import com.clover.repository.PetInitOutboxRepository;
import com.clover.service.KafkaProducer;
import com.clover.service.event.PetInitEvent;
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
public class PetInitEventHandler {

    private final PetInitOutboxRepository petInitOutboxRepository;
    private final KafkaProducer kafkaProducer;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleCompletion(PetInitEvent event) {
        log.info("pet outbox save: {}", event);
        petInitOutboxRepository.save(event.toOutbox());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PetInitEvent event) {
        log.info("Handling transactional event after commit: {}", event);
        kafkaProducer.send("init-pet", event);
        petInitOutboxRepository.deleteByEventId(event.eventId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleRollback(PetInitEvent event) {
        log.info("Handling transactional event rollback: {}", event);
    }
}
