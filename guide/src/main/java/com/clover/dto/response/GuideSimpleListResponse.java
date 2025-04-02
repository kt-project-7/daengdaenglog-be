package com.clover.dto.response;

import java.util.List;

public record GuideSimpleListResponse(
        List<GuideSimpleResponse> guideSimpleResponseList
) {

    public static GuideSimpleListResponse from(List<GuideSimpleResponse> guideSimpleResponseList) {
        return new GuideSimpleListResponse(guideSimpleResponseList);
    }
}
