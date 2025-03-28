package com.clover.repository.init;

import com.clover.domain.Pet;
import com.clover.domain.type.PetType;
import com.clover.repository.PetRepository;
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
public class PetInitializer implements ApplicationRunner {

    private final PetRepository petRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (petRepository.count() > 0) {
            log.info("[Pet]더미 데이터 존재");
            return;
        }

        List<Pet> petList = new ArrayList<>();

        Pet user1Dog = Pet.builder()
                .userId(1L)
                .name("멍멍이")
                .imageUri("")
                .pbti("ISTJ")
                .name("멍멍이")
                .petType(PetType.DOG)
                .build();
        Pet user1Cat = Pet.builder()
                .userId(1L)
                .name("야옹이")
                .imageUri("")
                .pbti("ENFP")
                .name("야옹이")
                .petType(PetType.CAT)
                .build();

        Pet user2Dog = Pet.builder()
                .userId(2L)
                .name("멍멍이")
                .imageUri("")
                .pbti("ISTJ")
                .name("멍멍이")
                .petType(PetType.DOG)
                .build();
        Pet user2Cat = Pet.builder()
                .userId(2L)
                .name("야옹이")
                .imageUri("")
                .pbti("ENFP")
                .name("야옹이")
                .petType(PetType.CAT)
                .build();

        petList.add(user1Dog);
        petList.add(user1Cat);
        petList.add(user2Dog);
        petList.add(user2Cat);

        petRepository.saveAll(petList);
    }
}
