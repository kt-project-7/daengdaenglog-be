package com.clover.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserType {
    ADMIN("UT-001", "관리자"),
    USER("UT-002", "사용자"),
    ;

    private final String code;
    private final String description;
}
