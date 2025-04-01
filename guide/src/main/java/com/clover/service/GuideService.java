package com.clover.service;

import com.clover.domain.Guide;
import com.clover.dto.request.GuideGenerateRequest;
import com.clover.repository.GuideRepository;
import com.clover.service.event.GuideInitEvent;
import com.clover.util.EventsUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepository guideRepository;
    private final EventsUtils eventsUtils;

    @Transactional
    public void guideInit(Long petId, GuideGenerateRequest request) {

        Guide guide = guideRepository.save(request.toEntity(petId));
        eventsUtils.raise(GuideInitEvent.of(guide.getId(), petId));
    }
}
