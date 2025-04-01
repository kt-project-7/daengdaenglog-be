package com.clover.dto.request;

import java.util.List;

public record SummaryDataRequest(
        String EmotionType,
        String WeatherType,
        String title,
        String content,
        List<SummaryDataScheduleRequest> summaryDataScheduleRequestList
) {

}
