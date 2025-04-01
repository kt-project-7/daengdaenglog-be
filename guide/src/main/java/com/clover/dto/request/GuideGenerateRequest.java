package com.clover.dto.request;

import com.clover.domain.Guide;
import com.clover.domain.type.GuideType;
import com.clover.domain.type.Status;

public record GuideGenerateRequest(
        GuideType guideType,
        String description
) {

    public Guide toEntity(Long petId) {
        return Guide.builder()
                .petId(petId)
                .status(Status.PENDING)
                .build();
    }
}
