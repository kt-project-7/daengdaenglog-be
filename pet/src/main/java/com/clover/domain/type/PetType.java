package com.clover.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PetType {
    DOG("PT-001"),
    CAT("PT-002"),
    BIRD("PT-003"),
    ;

    private final String code;
}
