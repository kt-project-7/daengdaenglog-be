package com.clover.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmotionType {
    HAPPY("PT-EMO-001", "행복"),
    SAD("PT-EMO-002", "슬픔"),
    ANGRY("PT-EMO-003", "화남"),
    SURPRISED("PT-EMO-004", "놀람"),
    HUNGRY("PT-EMO-005", "배고픔"),
    SICK("PT-EMO-006", "아픔"),
    LOVE("PT-EMO-007", "사랑"),
    SLEEPY("PT-EMO-008", "졸림"),
    ;

    private final String code;
    private final String description;
}
