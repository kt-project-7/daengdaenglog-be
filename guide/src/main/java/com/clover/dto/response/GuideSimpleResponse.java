package com.clover.dto.response;

import com.clover.domain.Guide;
import com.clover.domain.type.GuideType;
import com.clover.domain.type.Status;

import java.time.LocalDateTime;

public record GuideSimpleResponse(
        Long guideId,
        String petName,
        Status status,
        GuideType guideType,
        LocalDateTime createdDate
) {

    public static GuideSimpleResponse of(Guide guide, String petName) {
        return new GuideSimpleResponse(
                guide.getId(),
                petName,
                guide.getStatus(),
                guide.getGuideType(),
                guide.getCreatedDate()
        );
    }
}
