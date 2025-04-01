package com.clover.dto.request;

import com.clover.domain.ScheduleTime;

public record SummaryDataScheduleRequest(
        String scheduleType,
        int startHour, // 시
        int startMinute, // 분
        int endHour, // 시
        int endMinute // 분
) {
    public static SummaryDataScheduleRequest from(ScheduleTime scheduleTime) {
        return new SummaryDataScheduleRequest(
                scheduleTime.getScheduleType().name(),
                scheduleTime.getStartTime().getHour(),
                scheduleTime.getStartTime().getMinute(),
                scheduleTime.getEndTime().getHour(),
                scheduleTime.getEndTime().getMinute()
        );
    }
}
