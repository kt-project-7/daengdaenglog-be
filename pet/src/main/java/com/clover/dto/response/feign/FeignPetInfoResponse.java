package com.clover.dto.response.feign;

import com.clover.domain.Pet;
import lombok.Builder;

@Builder
public record FeignPetInfoResponse(
        Long petId,
        String name,
        String imageUri,
        String pbti,
        String petType
) {
    public static FeignPetInfoResponse from(Pet pet) {
        return FeignPetInfoResponse.builder()
                .petId(pet.getId())
                .name(pet.getName())
                .imageUri(pet.getImageUri())
                .pbti(pet.getPbti())
                .petType(pet.getPetType().name())
                .build();
    }
}
