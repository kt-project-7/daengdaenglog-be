package com.clover.repository.init;

import com.clover.domain.Diary;
import com.clover.domain.Memory;
import com.clover.repository.DiaryRepository;
import com.clover.repository.MemoryRepository;
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
public class MemoryInitializer implements ApplicationRunner {

    private final DiaryRepository diaryRepository;
    private final MemoryRepository memoryRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (memoryRepository.count() > 0) {
            log.info("[Memory]더미 데이터 존재");
            return;
        }
        Diary diary1 = diaryRepository.findById(1L).orElseThrow();

        List<Memory> memoryList = new ArrayList<>();

        Memory memory1 = Memory.builder()
                .diary(diary1)
                .imageUri("")
                .build();

        memoryList.add(memory1);

        memoryRepository.saveAll(memoryList);
    }
}
