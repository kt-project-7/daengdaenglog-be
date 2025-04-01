package com.clover.dto.response;

import java.util.List;

public record PetInsuranceListResponse(
        List<PetInsuranceResponse> petInsuranceList
) {

    public static PetInsuranceListResponse from(List<PetInsuranceResponse> petInsuranceList) {
        return new PetInsuranceListResponse(petInsuranceList);
    }
}
