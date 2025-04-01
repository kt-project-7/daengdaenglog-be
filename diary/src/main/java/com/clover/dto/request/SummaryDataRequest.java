package com.clover.dto.request;

import com.clover.domain.Diary;

import java.util.List;

public record SummaryDataRequest(
        String EmotionType,
        String WeatherType,
        String title,
        String content,
        List<SummaryDataScheduleRequest> summaryDataScheduleRequestList
) {

    public static SummaryDataRequest from(Diary diary) {
        return new SummaryDataRequest(
                diary.getEmotionType().name(),
                diary.getWeatherType().name(),
                diary.getTitle(),
                diary.getContent(),
                diary.getScheduleTimeList().stream()
                        .map(SummaryDataScheduleRequest::from)
                        .toList()
        );
    }
}
