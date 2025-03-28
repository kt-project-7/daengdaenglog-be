package com.clover.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ScheduleType {
    WALK("PT-SCH-001", "산책"),
    FEED("PT-SCH-002", "밥주기"),
    ;

    private final String code;
    private final String description;
}
