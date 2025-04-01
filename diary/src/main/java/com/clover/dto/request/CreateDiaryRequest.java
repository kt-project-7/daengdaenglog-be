package com.clover.dto.request;

import com.clover.domain.Diary;
import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;

import java.util.List;

public record CreateDiaryRequest(
        Long petId,
        EmotionType emotionType,
        WeatherType weatherType,
        String title,
        String content,
        List<DiaryScheduleRequest> diaryScheduleRequestList
) {

    public Diary toEntity(String memoryImageUrl) {
        return Diary.builder()
                .petId(this.petId())
                .emotionType(this.emotionType())
                .weatherType(this.weatherType())
                .title(this.title())
                .content(this.content())
                .memoryUri(memoryImageUrl)
                .scheduleTimeList(this.diaryScheduleRequestList().stream()
                        .map(DiaryScheduleRequest::toEntity)
                        .toList())
                .build();
    }
}
