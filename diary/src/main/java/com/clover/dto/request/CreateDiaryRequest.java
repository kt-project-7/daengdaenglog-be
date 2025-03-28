package com.clover.dto.request;

import com.clover.domain.Diary;
import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;

public record CreateDiaryRequest(
        Long petId,
        EmotionType emotionType,
        WeatherType weatherType,
        String title,
        String content
) {

    public Diary toEntity() {
        return Diary.builder()
                .petId(this.petId())
                .emotionType(this.emotionType())
                .weatherType(this.weatherType())
                .title(this.title())
                .content(this.content())
                .build();
    }
}
