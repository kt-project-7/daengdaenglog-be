package com.clover.dto.response;

import java.util.List;

public record DiarySimpleListResponse(
        List<DiarySimpleResponse> diarySimpleResponseList
) {
    public static DiarySimpleListResponse from(List<DiarySimpleResponse> diarySimpleResponseList) {
        return new DiarySimpleListResponse(diarySimpleResponseList);
    }
}
