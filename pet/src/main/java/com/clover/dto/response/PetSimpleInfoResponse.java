package com.clover.dto.response;

import com.clover.domain.Pet;
import com.clover.domain.type.Gender;
import lombok.Builder;

@Builder
public record PetSimpleInfoResponse(
        Long id,
        String name,
        String breed,
        Integer age,
        Double weight,
        Gender gender,
        Boolean neutered,
        String imageUri,
        String pbti,
        String petType
) {
    public static PetSimpleInfoResponse from(Pet pet) {
        return PetSimpleInfoResponse.builder()
                .id(pet.getId())
                .name(pet.getName())
                .breed(pet.getBreed())
                .age(pet.getAge())
                .weight(pet.getWeight())
                .gender(pet.getGender())
                .neutered(pet.getNeutered())
                .imageUri(pet.getImageUri())
                .pbti(pet.getPbti())
                .petType(pet.getPetType().name())
                .build();
    }
}
