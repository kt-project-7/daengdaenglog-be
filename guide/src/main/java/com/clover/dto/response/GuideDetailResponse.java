package com.clover.dto.response;

import com.clover.domain.Guide;
import com.clover.domain.type.Status;

import java.time.LocalDateTime;

public record GuideDetailResponse(
        Long guideId,
        Status status,
        LocalDateTime createdDate,
        String content
) {

    public static GuideDetailResponse from(Guide guide) {
        return new GuideDetailResponse(
                guide.getId(),
                guide.getStatus(),
                guide.getCreatedDate(),
                guide.getContent()
        );
    }
}
