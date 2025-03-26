package com.clover.repository.init;

import com.clover.domain.Code;
import com.clover.repository.CodeRepository;
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
@Order(1)
@DummyDataInit
public class CodeInitializer implements ApplicationRunner {

    private final CodeRepository codeRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (codeRepository.count() > 0) {
            log.info("[Code]더미 데이터 존재");
            return;
        }

        List<Code> codeList = new ArrayList<>();

        Code userGroup = Code.builder()
                .code("UT")
                .value("유저 그룹")
                .description("유저 코드 그룹")
                .build();
        Code admin = Code.builder()
                .code("UT-001")
                .value("ADMIN")
                .description("관리자 코드")
                .build();
        Code user = Code.builder()
                .code("UT-002")
                .value("USER")
                .description("사용자 코드")
                .build();

        Code petGroup = Code.builder()
                .code("PT")
                .value("펫 그룹")
                .description("펫 코드 그룹")
                .build();
        Code dog = Code.builder()
                .code("PT-001")
                .value("DOG")
                .description("강아지 코드")
                .build();
        Code cat = Code.builder()
                .code("PT-002")
                .value("CAT")
                .description("고양이 코드")
                .build();
        Code bird = Code.builder()
                .code("PT-003")
                .value("BIRD")
                .description("새 코드")
                .build();

        Code insuranceGroup = Code.builder()
                .code("IT")
                .value("보험 그룹")
                .description("보험 코드 그룹")
                .build();
        Code death = Code.builder()
                .code("IT-001")
                .value("DEATH")
                .description("사망 보험 코드")
                .build();
        Code general = Code.builder()
                .code("IT-002")
                .value("GENERAL")
                .description("일반 보험 코드")
                .build();

        codeList.add(userGroup);
        codeList.add(admin);
        codeList.add(user);

        codeList.add(petGroup);
        codeList.add(dog);
        codeList.add(cat);
        codeList.add(bird);

        codeList.add(insuranceGroup);
        codeList.add(death);
        codeList.add(general);

        codeRepository.saveAll(codeList);
    }
}
