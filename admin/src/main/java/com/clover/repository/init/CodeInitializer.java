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

        Code petScheduleWalk = Code.builder()
                .code("PT-SCH-001")
                .value("WALK")
                .description("산책 코드")
                .build();
        Code petScheduleFeed = Code.builder()
                .code("PT-SCH-002")
                .value("FEED")
                .description("먹이 주기 코드")
                .build();

        Code petEmotionHappy = Code.builder()
                .code("PT-EMO-001")
                .value("HAPPY")
                .description("행복한 감정 코드")
                .build();
        Code petEmotionSad = Code.builder()
                .code("PT-EMO-002")
                .value("SAD")
                .description("슬픈 감정 코드")
                .build();
        Code petEmotionAngry = Code.builder()
                .code("PT-EMO-003")
                .value("ANGRY")
                .description("화난 감정 코드")
                .build();
        Code petEmotionSurprised = Code.builder()
                .code("PT-EMO-004")
                .value("SURPRISED")
                .description("놀란 감정 코드")
                .build();
        Code petEmotionHungry = Code.builder()
                .code("PT-EMO-005")
                .value("HUNGRY")
                .description("배고픈 감정 코드")
                .build();
        Code petEmotionSick = Code.builder()
                .code("PT-EMO-006")
                .value("SICK")
                .description("아픈 감정 코드")
                .build();
        Code petEmotionLove = Code.builder()
                .code("PT-EMO-007")
                .value("LOVE")
                .description("사랑하는 감정 코드")
                .build();
        Code petEmotionSleepy = Code.builder()
                .code("PT-EMO-008")
                .value("SLEEPY")
                .description("졸린 감정 코드")
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

        codeList.add(petScheduleWalk);
        codeList.add(petScheduleFeed);

        codeList.add(petEmotionHappy);
        codeList.add(petEmotionSad);
        codeList.add(petEmotionAngry);
        codeList.add(petEmotionSurprised);
        codeList.add(petEmotionHungry);
        codeList.add(petEmotionSick);
        codeList.add(petEmotionLove);
        codeList.add(petEmotionSleepy);

        codeList.add(insuranceGroup);
        codeList.add(death);
        codeList.add(general);

        codeRepository.saveAll(codeList);
    }
}
