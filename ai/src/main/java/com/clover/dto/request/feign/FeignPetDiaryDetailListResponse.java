package com.clover.dto.request.feign;

import lombok.Builder;

import java.util.List;

@Builder
public record FeignPetDiaryDetailListResponse(
        List<FeignPetDiaryDetailResponse> diaryList
) {

    public static FeignPetDiaryDetailListResponse from( List<FeignPetDiaryDetailResponse> diaryList) {
        return FeignPetDiaryDetailListResponse.builder()
                .diaryList(diaryList)
                .build();
    }
}
