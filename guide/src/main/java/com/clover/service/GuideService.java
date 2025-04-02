package com.clover.service;

import com.clover.domain.Guide;
import com.clover.dto.request.GuideGenerateRequest;
import com.clover.dto.response.GuideDetailResponse;
import com.clover.dto.response.GuideSimpleListResponse;
import com.clover.dto.response.GuideSimpleResponse;
import com.clover.dto.response.feign.FeignPetInfoResponse;
import com.clover.repository.GuideInitOutboxRepository;
import com.clover.repository.GuideRepository;
import com.clover.service.client.PetClient;
import com.clover.service.event.GuideInitEvent;
import com.clover.util.EventsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final GuideInitOutboxRepository guideInitOutboxRepository;
    private final EventsUtils eventsUtils;
    private final PetClient petClient;

    @Transactional
    public void guideInit(Long petId, GuideGenerateRequest request) {
        Guide guide = guideRepository.save(request.toEntity(petId));
        eventsUtils.raise(GuideInitEvent.of(guide.getId(), petId, request.guideType()));
    }

    public GuideSimpleListResponse getGuideList(Long userId) {
        List<FeignPetInfoResponse> petInfoList = petClient.getPetInfoList(userId);

        List<Long> petIdList = petInfoList.stream()
                .map(FeignPetInfoResponse::petId)
                .toList();

        List<GuideSimpleResponse> list = guideRepository.findAllByPetIdInOrderByCreatedDateDesc(petIdList).stream()
                .map(guide -> GuideSimpleResponse.of(guide, petInfoList.stream()
                        .filter(petInfo -> petInfo.petId().equals(guide.getPetId()))
                        .findFirst()
                        .map(FeignPetInfoResponse::name)
                        .orElseThrow()))
                .toList();

        return GuideSimpleListResponse.from(list);
    }

    public GuideDetailResponse getGuideDetail(Long guideId) {
        Guide guide = guideRepository.findById(guideId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가이드가 없습니다."));

        return GuideDetailResponse.from(guide);
    }

    public void regenerateGuide() {
        guideInitOutboxRepository.findAllByIsDeletedFalse()
                .forEach(outbox -> eventsUtils.raise(GuideInitEvent.of(outbox.getGuideId(), outbox.getPetId(), outbox.getGuideType())));
    }
}
