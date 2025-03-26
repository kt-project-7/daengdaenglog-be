package com.clover.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserType {
    ADMIN("UT-001"),
    USER("UT-002"),
    ;

    private final String code;
}
