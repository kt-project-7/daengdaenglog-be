package com.clover.dto.response;

public record SummaryResponse(
        Long petId,
        Long year,
        Long month,
        String summary
) {
}
