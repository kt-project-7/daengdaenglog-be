package com.clover.dto.response;

import com.clover.domain.Diary;
import com.clover.domain.Memory;
import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record DiaryDetailResponse(
        Long diaryId,
        Long petId,
        String title,
        String content,
        EmotionType emotionType,
        WeatherType weatherType,
        LocalDate createdDate,
        Long memoryId,
        String memoryUri,
        List<DiaryDetailScheduleResponse> scheduleList
) {

    public static DiaryDetailResponse from(Diary diary, Memory memory) {
        return DiaryDetailResponse.builder()
                .diaryId(diary.getId())
                .petId(diary.getPetId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .emotionType(diary.getEmotionType())
                .weatherType(diary.getWeatherType())
                .createdDate(diary.getCreatedDate().toLocalDate())
                .memoryId(memory == null ? 0L : memory.getId())
                .memoryUri(memory == null ? "" : memory.getImageUri())
                .scheduleList(diary.getScheduleTimeList().stream()
                        .map(DiaryDetailScheduleResponse::from)
                        .toList())
                .build();
    }
}
