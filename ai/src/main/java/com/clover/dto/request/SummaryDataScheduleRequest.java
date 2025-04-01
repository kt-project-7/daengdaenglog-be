package com.clover.dto.request;

public record SummaryDataScheduleRequest(
        String scheduleType,
        int startHour, // 시
        int startMinute, // 분
        int endHour, // 시
        int endMinute // 분
) {
}
