package com.clover.dto.response;

import com.clover.dto.response.feign.FeignPetInfoResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record PetDiaryResponse(
        Long petId,
        String name,
        String imageUri,
        String pbti,
        String petType,
        List<DiarySimpleResponse> diaryList
) {

    public static PetDiaryResponse of(FeignPetInfoResponse pet, List<DiarySimpleResponse> diaryList) {
        return PetDiaryResponse.builder()
                .petId(pet.petId())
                .name(pet.name())
                .imageUri(pet.imageUri())
                .pbti(pet.pbti())
                .petType(pet.petType())
                .diaryList(diaryList)
                .build();
    }
}
