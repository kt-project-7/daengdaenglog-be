package com.clover.dto.response;

import java.util.List;

public record PetSimpleInfoListResponse(
        List<PetSimpleInfoResponse> petSimpleInfoResponseList
) {

    public static PetSimpleInfoListResponse from(List<PetSimpleInfoResponse> petSimpleInfoResponseList) {
        return new PetSimpleInfoListResponse(petSimpleInfoResponseList);
    }
}
