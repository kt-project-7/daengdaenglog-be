package com.clover.service.handler;

import com.clover.dto.request.UserInitRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class UserInitEventHandler {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(UserInitRequest event) {
        log.info("Handling transactional event after commit: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleRollback(UserInitRequest event) {
        log.info("Handling transactional event rollback: {}", event);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleCompletion(UserInitRequest event) {
        log.info("Handling transactional event before commit: {}", event);
    }
}
