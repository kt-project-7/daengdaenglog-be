package com.clover.dto.response;

import com.clover.domain.InsuranceClaim;
import com.clover.domain.type.ProgressType;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record PetInsuranceResponse(
        String hospitalName,
        Long medicalFee,
        Long claimAmount,
        Long refundAmount,
        String description,
        ProgressType ProgressType,
        LocalDate date
) {

    public static PetInsuranceResponse from(InsuranceClaim insuranceClaim) {
        return PetInsuranceResponse.builder()
                .hospitalName(insuranceClaim.getHospitalName())
                .medicalFee(insuranceClaim.getMedicalFee())
                .claimAmount(insuranceClaim.getClaimAmount())
                .refundAmount(insuranceClaim.getRefundAmount())
                .description(insuranceClaim.getDescription())
                .ProgressType(insuranceClaim.getProgressType())
                .date(insuranceClaim.getCreatedDate().toLocalDate())
                .build();
    }
}