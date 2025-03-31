package com.clover.dto.request;

import com.clover.domain.ScheduleTime;
import com.clover.domain.type.ScheduleType;

import java.time.LocalTime;

public record DiaryScheduleRequest(
        ScheduleType scheduleType,
        int startHour, // 시
        int startMinute, // 분
        int endHour, // 시
        int endMinute // 분
) {

    public ScheduleTime toEntity() {
        // LocalTime으로 변환하여 엔티티 생성
        return ScheduleTime.builder()
                .scheduleType(this.scheduleType())
                .startTime(LocalTime.of(this.startHour(), this.startMinute()))  // 시, 분으로 LocalTime 생성
                .endTime(LocalTime.of(this.endHour(), this.endMinute()))  // 시, 분으로 LocalTime 생성
                .build();
    }
}
