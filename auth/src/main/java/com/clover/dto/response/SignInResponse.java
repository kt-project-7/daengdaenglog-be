package com.clover.dto.response;

public record SignInResponse(
        String userName,
        String imageUri
) {
    public static SignInResponse of(String userName, String imageUri) {
        return new SignInResponse(userName, imageUri);
    }
}
