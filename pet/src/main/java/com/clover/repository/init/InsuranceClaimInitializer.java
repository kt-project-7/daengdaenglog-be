package com.clover.repository.init;

import com.clover.domain.InsuranceClaim;
import com.clover.repository.InsuranceClaimRepository;
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

    private final InsuranceClaimRepository insuranceClaimRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (insuranceClaimRepository.count() > 0) {
            log.info("[InsuranceClaim]더미 데이터 존재");
            return;
        }

        List<InsuranceClaim> insuranceList = new ArrayList<>();

        //TODO: 보험 청구 데이터 추가

        insuranceClaimRepository.saveAll(insuranceList);
    }
}
