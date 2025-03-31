package com.clover.repository.init;

import com.clover.domain.Insurance;
import com.clover.domain.InsuranceClaim;
import com.clover.domain.type.ProgressType;
import com.clover.repository.InsuranceClaimRepository;
import com.clover.repository.InsuranceRepository;
import com.clover.util.DummyDataInit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(3)
@DummyDataInit
public class InsuranceClaimInitializer implements ApplicationRunner {

    private final InsuranceRepository insuranceRepository;
    private final InsuranceClaimRepository insuranceClaimRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (insuranceClaimRepository.count() > 0) {
            log.info("[InsuranceClaim]더미 데이터 존재");
            return;
        }

        Insurance insurance1 = insuranceRepository.findById(1L).orElseThrow();
        Insurance insurance2 = insuranceRepository.findById(2L).orElseThrow();

        List<InsuranceClaim> insuranceList = new ArrayList<>();

        InsuranceClaim insuranceClaim1 = InsuranceClaim.builder()
                .insurance(insurance1)
                .hospitalName("병원1")
                .medicalFee(50000L)
                .claimAmount(10000L)
                .refundAmount(8000L)
                .description("병원1 진료비 청구")
                .progressType(ProgressType.APPROVED)
                .build();
        InsuranceClaim insuranceClaim2 = InsuranceClaim.builder()
                .insurance(insurance1)
                .hospitalName("병원2")
                .medicalFee(100000L)
                .claimAmount(20000L)
                .refundAmount(15000L)
                .description("병원2 진료비 청구")
                .progressType(ProgressType.APPROVED)
                .build();

        InsuranceClaim insuranceClaim3 = InsuranceClaim.builder()
                .insurance(insurance2)
                .hospitalName("병원3")
                .medicalFee(200000L)
                .claimAmount(50000L)
                .refundAmount(40000L)
                .description("병원3 진료비 청구")
                .progressType(ProgressType.APPROVED)
                .build();

        insuranceList.add(insuranceClaim1);
        insuranceList.add(insuranceClaim2);
        insuranceList.add(insuranceClaim3);

        insuranceClaimRepository.saveAll(insuranceList);
    }
}
