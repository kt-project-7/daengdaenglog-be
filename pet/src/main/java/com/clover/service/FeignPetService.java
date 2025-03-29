package com.clover.service;

import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    public boolean validatePetId(Long petId, Long userId) {
        return petRepository.existsByIdAndUserId(petId, userId);
    }
}
