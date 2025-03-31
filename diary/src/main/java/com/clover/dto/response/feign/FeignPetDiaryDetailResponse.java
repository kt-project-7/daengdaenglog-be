package com.clover.dto.response.feign;

import com.clover.domain.Diary;
import lombok.Builder;

@Builder
public record FeignPetDiaryDetailResponse(
        Long diaryId,
        Long petId,
        String title,
        String content,
        String emotionType,
        String weatherType,
        String createdDate,
        String memoryUri
) {

    public static FeignPetDiaryDetailResponse from(Diary diary) {
        return FeignPetDiaryDetailResponse.builder()
                .diaryId(diary.getId())
                .petId(diary.getPetId())
                .title(diary.getTitle())
                .content(diary.getContent())
                .emotionType(diary.getEmotionType().name())
                .weatherType(diary.getWeatherType().name())
                .createdDate(diary.getCreatedDate().toLocalDate().toString())
                .memoryUri(diary.getMemoryUri())
                .build();
    }
}
