package com.clover.dto.request.feign;

import lombok.Builder;

import java.time.LocalTime;

@Builder
public record FeignImageGenScheduleRequest(
        String scheduleType,
        LocalTime startTime,
        LocalTime endTime
) {
}
