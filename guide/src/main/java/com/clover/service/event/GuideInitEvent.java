package com.clover.service.event;

import com.clover.domain.GuideInitOutbox;
import com.clover.domain.type.Status;
import lombok.Builder;

@Builder
public record GuideInitEvent(
        Long guideId
) {

    public GuideInitOutbox toOutbox() {
        return GuideInitOutbox.builder()
                .guideId(guideId)
                .status(Status.PENDING)
                .build();
    }
}
