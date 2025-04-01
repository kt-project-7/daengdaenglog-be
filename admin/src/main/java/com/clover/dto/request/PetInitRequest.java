package com.clover.dto.request;

import com.clover.domain.PetInitOutbox;
import com.clover.domain.type.Status;

public record PetInitRequest(
        Long userId,
        String name,
        String petType
) {

    public PetInitOutbox toOutbox() {
        return PetInitOutbox.builder()
                .userId(userId)
                .name(name)
                .petType(petType)
                .status(Status.PENDING)
                .build();
    }
}
