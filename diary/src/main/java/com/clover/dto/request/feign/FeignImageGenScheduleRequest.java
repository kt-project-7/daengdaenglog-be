package com.clover.dto.request.feign;

import com.clover.domain.ScheduleTime;
import com.clover.domain.type.ScheduleType;
import lombok.Builder;

import java.time.LocalTime;

@Builder
public record FeignImageGenScheduleRequest(
        ScheduleType scheduleType,
        LocalTime startTime,
        LocalTime endTime
) {

    public static FeignImageGenScheduleRequest from(ScheduleTime scheduleTime) {
        return FeignImageGenScheduleRequest.builder()
                .scheduleType(scheduleTime.getScheduleType())
                .startTime(scheduleTime.getStartTime())
                .endTime(scheduleTime.getEndTime())
                .build();
    }
}
