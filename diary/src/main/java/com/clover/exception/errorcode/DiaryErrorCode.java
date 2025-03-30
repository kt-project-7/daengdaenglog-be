package com.clover.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum DiaryErrorCode implements ErrorCode {
    PET_ID_NOT_MATCH(HttpStatus.BAD_REQUEST, "Pet ID does not match User's Pet."),
    DIARY_NOT_FOUND(HttpStatus.NOT_FOUND, "Diary not found."),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
