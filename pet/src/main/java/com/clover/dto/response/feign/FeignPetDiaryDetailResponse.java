package com.clover.dto.response.feign;

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
}
