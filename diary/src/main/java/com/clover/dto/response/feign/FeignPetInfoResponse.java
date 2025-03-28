package com.clover.dto.response.feign;

public record FeignPetInfoResponse(
        Long petId,
        String name,
        String imageUri,
        String pbti,
        String petType
) {
}
