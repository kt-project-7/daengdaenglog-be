package com.clover.dto.response;

public record GuideGenerateResponse(
        Long id,
        Long petId,
        String generalInfo,
        String routineInfo,
        String feedingInfo,
        String healthInfo,
        String specialInfo,
        String emergencyInfo
) {
}
