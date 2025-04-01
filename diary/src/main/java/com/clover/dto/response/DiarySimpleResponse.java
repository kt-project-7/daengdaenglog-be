package com.clover.dto.response;

import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiarySimpleResponse(
        Long diaryId,
        Long petId,
        String title,
        String content,
        EmotionType emotionType,
        WeatherType weatherType,
        LocalDateTime createdDate,
        String memoryUri,
        String generatedImageUri
) {

    @JsonProperty("createdDate")
    public LocalDate getCreatedDate() {
        return createdDate.toLocalDate();
    }
}
