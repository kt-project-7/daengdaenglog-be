package com.clover.dto.response;

import com.clover.domain.Pet;
import lombok.Builder;

@Builder
public record PetSimpleInfoResponse(
        Long petId,
        String name,
        String imageUri,
        String pbti,
        String petType
) {
    public static PetSimpleInfoResponse from(Pet pet) {
        return PetSimpleInfoResponse.builder()
                .petId(pet.getId())
                .name(pet.getName())
                .imageUri(pet.getImageUri())
                .pbti(pet.getPbti())
                .petType(pet.getPetType().name())
                .build();
    }
}
