package com.clover.dto.response.feign;

import com.clover.domain.Pet;
import lombok.Builder;

@Builder
public record FeignPetInfoResponse(
        Long petId,
        String name,
        String imageUri,
        String pbti,
        String petType,
        String breed,
        Integer age,
        String gender
) {
    public static FeignPetInfoResponse from(Pet pet) {
        return FeignPetInfoResponse.builder()
                .petId(pet.getId())
                .name(pet.getName())
                .imageUri(pet.getImageUri())
                .pbti(pet.getPbti())
                .petType(pet.getPetType().name())
                .breed(pet.getBreed())
                .age(pet.getAge())
                .gender(pet.getGender().name())
                .build();
    }
}
