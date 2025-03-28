package com.clover.repository.init;

import com.clover.domain.User;
import com.clover.domain.UserType;
import com.clover.repository.UserRepository;
import com.clover.util.DummyDataInit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(1)
@DummyDataInit
public class UserInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            log.info("[User]더미 데이터 존재");
            return;
        }

        List<User> userList = new ArrayList<>();

        User user1 = User.builder()
                .name("user1")
                .email("user1@email.com")
                .userType(UserType.ADMIN)
                .imageUri("https://www.google.com")
                .password(passwordEncoder.encode("password"))
                .phoneNumber("01012345678")
                .build();

        User user2 = User.builder()
                .name("user2")
                .email("user2@email.com")
                .userType(UserType.USER)
                .imageUri("https://www.google.com")
                .password(passwordEncoder.encode("password"))
                .phoneNumber("01023456789")
                .build();

        User user3 = User.builder()
                .name("user3")
                .email("user3@email.com")
                .userType(UserType.USER)
                .imageUri("https://www.google.com")
                .password(passwordEncoder.encode("password"))
                .phoneNumber("01034567890")
                .build();

        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        userRepository.saveAll(userList);
    }
}
