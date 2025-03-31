package com.clover.dto.response;

import com.clover.domain.ScheduleTime;
import com.clover.domain.type.ScheduleType;
import java.time.LocalTime;

public record DiaryDetailScheduleResponse(
        Long scheduleId,
        ScheduleType scheduleType,
        LocalTime startTime,
        LocalTime endTime
) {

    public static DiaryDetailScheduleResponse from(ScheduleTime diarySchedule) {
        return new DiaryDetailScheduleResponse(
                diarySchedule.getId(),
                diarySchedule.getScheduleType(),
                diarySchedule.getStartTime(),
                diarySchedule.getEndTime()
        );
    }
}
