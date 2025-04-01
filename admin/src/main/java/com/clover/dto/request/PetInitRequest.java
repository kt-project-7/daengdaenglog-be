package com.clover.dto.request;

public record PetInitRequest(
        Long userId,
        String imageUri,
        String pbti,
        String name,
        String petType
) {
}
