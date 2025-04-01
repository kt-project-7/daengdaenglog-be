package com.clover.dto.response;

public record GuideResponse(
        Long petId,
        Long guideId,
        String description
) {
}
