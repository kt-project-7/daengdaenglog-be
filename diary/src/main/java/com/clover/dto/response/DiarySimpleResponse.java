package com.clover.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DiarySimpleResponse(
        Long diaryId,
        String title,
        String content,
        LocalDateTime createdDate
) {

    @JsonProperty("createdDate")
    public LocalDate getCreatedDate() {
        return createdDate.toLocalDate();
    }
}
