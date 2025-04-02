package com.clover.dto.response;

import com.clover.dto.request.GuideInitRequest;

public record GuideResponse(
        Long petId,
        Long guideId,
        String description
) {

    public static GuideResponse from(GuideInitRequest request, String description) {
        return new GuideResponse(request.petId(), request.guideId(), description);
    }
}
