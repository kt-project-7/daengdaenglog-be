package com.clover.service;

import com.clover.dto.response.PetDiaryListResponse;
import com.clover.dto.response.DiarySimpleResponse;
import com.clover.dto.response.PetDiaryResponse;
import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.repository.DiaryRepository;
import com.clover.service.client.PetClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final PetClient petClient;

    public PetDiaryListResponse getPetDiaryList(
            Long userId, int size
    ) {
        List<FeignPetInfoResponse> petInfoList = petClient.getPetIdList(userId);

        petInfoList.forEach(petInfo -> log.info("petInfo: {}", petInfo));

        List<PetDiaryResponse> list = petInfoList.stream()
                .map(petInfo -> {
                    List<DiarySimpleResponse> diaryList = diaryRepository.getDiaryList(petInfo.petId(), 0, size);
                    return PetDiaryResponse.of(petInfo, diaryList);
                })
                .toList();

        return PetDiaryListResponse.from(list);
    }
}
