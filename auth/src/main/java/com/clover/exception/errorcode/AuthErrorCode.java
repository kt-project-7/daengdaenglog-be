package com.clover.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    TOKEN_NOT_VALID(HttpStatus.FORBIDDEN, "Refresh Token is Not Valid."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "Invalid Password"),
    PHONE_NUMBER_NOT_FOUND(HttpStatus.BAD_REQUEST, "Phone number is not found"),
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
