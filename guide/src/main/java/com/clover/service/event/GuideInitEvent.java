package com.clover.service.event;

import com.clover.domain.GuideInitOutbox;
import com.clover.domain.type.GuideType;
import lombok.Builder;

@Builder
public record GuideInitEvent(
        Long guideId,
        Long petId,
        GuideType guideType
) {

    public GuideInitOutbox toOutbox() {
        return GuideInitOutbox.builder()
                .guideId(guideId)
                .guideType(guideType)
                .build();
    }

    public static GuideInitEvent of(Long guideId, Long petId, GuideType guideType) {
        return GuideInitEvent.builder()
                .guideId(guideId)
                .petId(petId)
                .guideType(guideType)
                .build();
    }
}
