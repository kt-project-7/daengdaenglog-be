package com.clover.repository.init;

import com.clover.domain.Insurance;
import com.clover.domain.type.InsuranceType;
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
@Order(2)
@DummyDataInit
public class InsuranceInitializer implements ApplicationRunner {

    private final InsuranceRepository insuranceRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (insuranceRepository.count() > 0) {
            log.info("[Insurance]더미 데이터 존재");
            return;
        }

        List<Insurance> insuranceList = new ArrayList<>();

        Insurance insurance1 = Insurance.builder()
                .petId(1L)
                .insuranceType(InsuranceType.GENERAL)
                .build();
        Insurance insurance2 = Insurance.builder()
                .petId(1L)
                .insuranceType(InsuranceType.DEATH)
                .build();

        Insurance insurance3 = Insurance.builder()
                .petId(2L)
                .insuranceType(InsuranceType.GENERAL)
                .build();

        insuranceList.add(insurance1);
        insuranceList.add(insurance2);
        insuranceList.add(insurance3);

        insuranceRepository.saveAll(insuranceList);
    }
}
