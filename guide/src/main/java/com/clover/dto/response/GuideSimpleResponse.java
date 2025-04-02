package com.clover.dto.response;

import com.clover.domain.Guide;
import com.clover.domain.type.Status;

import java.time.LocalDateTime;

public record GuideSimpleResponse(
        Long guideId,
        Status status,
        LocalDateTime createdDate
) {

    public static GuideSimpleResponse from(Guide guide) {
        return new GuideSimpleResponse(
                guide.getId(),
                guide.getStatus(),
                guide.getCreatedDate()
        );
    }
}
