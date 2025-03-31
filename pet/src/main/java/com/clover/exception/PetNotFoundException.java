package com.clover.exception;

import com.clover.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PetNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;
}
