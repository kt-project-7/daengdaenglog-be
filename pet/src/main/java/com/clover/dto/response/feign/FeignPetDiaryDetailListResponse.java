package com.clover.dto.response.feign;

import lombok.Builder;

import java.util.List;

@Builder
public record FeignPetDiaryDetailListResponse(
        List<FeignPetDiaryDetailResponse> diaryList
) {
}
