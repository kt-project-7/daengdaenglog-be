package com.clover.service;

import com.clover.domain.Pet;
import com.clover.dto.response.PetInsuranceListResponse;
import com.clover.dto.response.PetInsuranceResponse;
import com.clover.dto.response.PetSimpleInfoListResponse;
import com.clover.dto.response.PetSimpleInfoResponse;
import com.clover.dto.response.feign.FeignPetDiaryDetailListResponse;
import com.clover.exception.PetNotFoundException;
import com.clover.exception.errorcode.PetErrorCode;
import com.clover.repository.InsuranceClaimRepository;
import com.clover.repository.PetRepository;
import com.clover.service.client.AiClient;
import com.clover.service.client.DiaryClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;
    private final InsuranceClaimRepository insuranceClaimRepository;
    private final DiaryClient diaryClient;
    private final AiClient aiClient;

    public PetSimpleInfoListResponse getPetList(
            Long userId
    ) {
        List<PetSimpleInfoResponse> list = petRepository.findAllByUserId(userId).stream()
                .map(PetSimpleInfoResponse::from)
                .toList();

        return PetSimpleInfoListResponse.from(list);
    }

    @Transactional
    public String analyzePetPbti(
            Long userId,
            Long petId
    ) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(PetErrorCode.PET_NOT_FOUND));

        if (!pet.getUserId().equals(userId)) {
            throw new PetNotFoundException(PetErrorCode.PET_ID_NOT_MATCH);
        }

        FeignPetDiaryDetailListResponse diaryList = diaryClient.getDiary(petId);

        diaryList.diaryList().forEach(diaryDetailResponse -> log.info(diaryDetailResponse.toString()));

        String pbti = aiClient.generatePbti(diaryList);
        pet. updatePbti(pbti);

        return pbti;
    }

    public PetInsuranceListResponse getPetInsurance(
            Long userId,
            Long petId
    ) {
        Pet pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException(PetErrorCode.PET_NOT_FOUND));

        if (!pet.getUserId().equals(userId)) {
            throw new PetNotFoundException(PetErrorCode.PET_ID_NOT_MATCH);
        }

        List<PetInsuranceResponse> list = insuranceClaimRepository.findAllByInsurance_PetId(petId).stream()
                .map(PetInsuranceResponse::from)
                .toList();

        return PetInsuranceListResponse.from(list);
    }
}
