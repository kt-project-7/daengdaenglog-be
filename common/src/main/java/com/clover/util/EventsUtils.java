package com.clover.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class EventsUtils {

    private final ApplicationEventPublisher publisher;

    public void raise(Object event) {
        publisher.publishEvent(event);
    }
}