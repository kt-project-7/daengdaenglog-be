package com.clover.dto.response;

import com.clover.domain.SummaryData;

public record SummaryResponse(
        Long petId,
        Long year,
        Long month,
        String summary
) {

    public SummaryData toEntity() {
        return SummaryData.builder()
                .petId(petId)
                .year(year)
                .month(month)
                .description(summary)
                .build();
    }
}
