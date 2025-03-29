package com.clover.dto.response;

public record AuthResponse(
        String userId
) {

    public static AuthResponse from(String userId) {
        return new AuthResponse(userId);
    }
}
