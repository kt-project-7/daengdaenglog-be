package com.clover.dto.request;

public record UserInitRequest(
        String phoneNumber,
        String password,
        String userType,
        String imageUri,
        String name,
        String email
) {
}
