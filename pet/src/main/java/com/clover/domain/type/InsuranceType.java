package com.clover.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InsuranceType {
    DEATH("IT-001", "사망 보험"),
    GENERAL("IT-002", "일반 보험"),
    ;

    private final String code;
    private final String description;
}
