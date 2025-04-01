package com.clover.dto.request;

import java.util.List;

public record SummaryRequest(
        Long petId,
        Long year,
        Long month,
        List<SummaryDataRequest> summaryDataList
) {
}
