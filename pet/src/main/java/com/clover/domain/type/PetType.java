package com.clover.domain.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PetType {
    DOG("PT-001", "강아지"),
    CAT("PT-002", "고양이"),
    ;

    private final String code;
    private final String description;
}
