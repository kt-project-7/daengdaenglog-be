package com.clover.dto.request;

import com.clover.domain.ScheduleTime;
import com.clover.domain.type.ScheduleType;

import java.time.LocalTime;

public record DiaryScheduleRequest(
        ScheduleType scheduleType,
        LocalTime startTime,
        LocalTime endTime
) {

    public ScheduleTime toEntity() {
        return ScheduleTime.builder()
                .scheduleType(this.scheduleType())
                .startTime(this.startTime())
                .endTime(this.endTime())
                .build();
    }
}
