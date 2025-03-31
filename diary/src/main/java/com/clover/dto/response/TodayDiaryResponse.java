package com.clover.dto.response;

public record TodayDiaryResponse(
        Boolean isWrite,
        Long diaryId
) {

    public static TodayDiaryResponse of(Boolean isWrite, Long diaryId) {
        return new TodayDiaryResponse(isWrite, diaryId);
    }
}
