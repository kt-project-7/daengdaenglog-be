package com.clover.repository.init;

import com.clover.domain.Diary;
import com.clover.domain.type.EmotionType;
import com.clover.domain.type.WeatherType;
import com.clover.repository.DiaryRepository;
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
public class DiaryInitializer implements ApplicationRunner {

    private final DiaryRepository diaryRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (diaryRepository.count() > 0) {
            log.info("[Diary]더미 데이터 존재");
            return;
        }

        List<Diary> diaryList = new ArrayList<>();

        Diary diary1 = Diary.builder()
                .petId(1L)
                .emotionType(EmotionType.ANGRY)
                .weatherType(WeatherType.CLOUDY)
                .title("title1")
                .content("content1")
                .build();
        Diary diary2 = Diary.builder()
                .petId(1L)
                .emotionType(EmotionType.HAPPY)
                .weatherType(WeatherType.SUNNY)
                .title("title2")
                .content("content2")
                .build();
        Diary diary3 = Diary.builder()
                .petId(1L)
                .emotionType(EmotionType.SAD)
                .weatherType(WeatherType.RAINY)
                .title("title3")
                .content("content3")
                .build();
        Diary diary4 = Diary.builder()
                .petId(1L)
                .emotionType(EmotionType.SAD)
                .weatherType(WeatherType.RAINY)
                .title("title4")
                .content("content4")
                .build();

        Diary diary5 = Diary.builder()
                .petId(2L)
                .emotionType(EmotionType.SAD)
                .weatherType(WeatherType.RAINY)
                .title("title5")
                .content("content5")
                .build();
        Diary diary6 = Diary.builder()
                .petId(2L)
                .emotionType(EmotionType.SAD)
                .weatherType(WeatherType.RAINY)
                .title("title6")
                .content("content6")
                .build();

        diaryList.add(diary1);
        diaryList.add(diary2);
        diaryList.add(diary3);
        diaryList.add(diary4);
        diaryList.add(diary5);
        diaryList.add(diary6);

        diaryRepository.saveAll(diaryList);
    }
}
