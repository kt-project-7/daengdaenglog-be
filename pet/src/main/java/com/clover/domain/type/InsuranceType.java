package com.clover.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InsuranceType {
    DEATH("IT-001"),
    GENERAL("IT-002"),
    ;

    private final String code;
}
