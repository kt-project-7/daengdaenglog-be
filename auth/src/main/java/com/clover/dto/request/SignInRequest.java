package com.clover.dto.request;

public record SignInRequest(
        String phoneNumber,
        String password
) {
}
