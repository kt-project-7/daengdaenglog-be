package com.clover.service.event;

import com.clover.domain.PetInitOutbox;
import com.clover.domain.type.Status;
import com.clover.dto.request.PetInitRequest;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PetInitEvent(
        Long userId,
        String name,
        String petType,
        String eventId
) {

    public static PetInitEvent from(PetInitRequest petInitRequest) {
        return PetInitEvent.builder()
                .userId(petInitRequest.userId())
                .name(petInitRequest.name())
                .petType(petInitRequest.petType())
                .eventId(UUID.randomUUID().toString())
                .build();
    }

    public PetInitOutbox toOutbox() {
        return PetInitOutbox.builder()
                .userId(userId)
                .name(name)
                .petType(petType)
                .eventId(eventId)
                .status(Status.PENDING)
                .build();
    }
}
