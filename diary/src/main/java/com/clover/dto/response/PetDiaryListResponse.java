package com.clover.dto.response;

import java.util.List;

public record PetDiaryListResponse(
        List<PetDiaryResponse> diaryList
) {

    public static PetDiaryListResponse from(List<PetDiaryResponse> diaryList) {
        return new PetDiaryListResponse(diaryList);
    }
}
