package com.clover.service;

import com.clover.domain.User;
import com.clover.exception.EmailNotFoundException;
import com.clover.exception.UserNotFoundException;
import com.clover.exception.errorcode.AuthErrorCode;
import com.clover.exception.errorcode.UserErrorCode;
import com.clover.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }

    User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new EmailNotFoundException(AuthErrorCode.EMAIL_NOT_FOUND));
    }
}
