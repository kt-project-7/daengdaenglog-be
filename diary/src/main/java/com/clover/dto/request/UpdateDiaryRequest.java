package com.clover.dto.request;

import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;

import java.util.List;

public record UpdateDiaryRequest(
        EmotionType emotionType,
        WeatherType weatherType,
        String title,
        String content,
        List<DiaryScheduleRequest> diaryScheduleRequestList
) {
}
