package com.clover.service.event;

import com.clover.domain.GuideInitOutbox;
import lombok.Builder;

@Builder
public record GuideInitEvent(
        Long guideId,
        Long petId
) {

    public GuideInitOutbox toOutbox() {
        return GuideInitOutbox.builder()
                .guideId(guideId)
                .build();
    }

    public static GuideInitEvent of(Long guideId, Long petId) {
        return GuideInitEvent.builder()
                .guideId(guideId)
                .petId(petId)
                .build();
    }
}
