package com.clover.service;

import com.clover.dto.response.PetSimpleInfoListResponse;
import com.clover.dto.response.PetSimpleInfoResponse;
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
public class PetService {

    private final PetRepository petRepository;

    public PetSimpleInfoListResponse getPetList(
            Long userId
    ) {
        List<PetSimpleInfoResponse> list = petRepository.findAllByUserId(userId).stream()
                .map(PetSimpleInfoResponse::from)
                .toList();

        return PetSimpleInfoListResponse.from(list);
    }
}
