package com.clover.service;

import com.clover.domain.Pet;
import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class FeignPetService {

    private final PetRepository petRepository;

    public List<FeignPetInfoResponse> getPetIdList(Long userId) {

        List<FeignPetInfoResponse> petInfoList = petRepository.findAllByUserId(userId).stream()
                .map(FeignPetInfoResponse::from)
                .toList();

        petInfoList.forEach(petInfo -> log.info("petInfo: {}", petInfo));

        return petInfoList;
    }

    public FeignPetInfoResponse getPetInfo(Long petId) {
        return petRepository.findById(petId)
                .map(FeignPetInfoResponse::from)
                .orElseThrow(() -> new RuntimeException("펫 정보를 찾을 수 없습니다."));
    }

    public boolean validatePetId(Long petId, Long userId) {
        return petRepository.existsByIdAndUserId(petId, userId);
    }

    public List<Long> getAllPetId() {
        return petRepository.findAll().stream().map(Pet::getId).toList();
    }
}
