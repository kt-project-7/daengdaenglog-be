package com.clover.service;

import com.clover.domain.User;
import com.clover.exception.PhoneNumberNotFoundException;
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

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));
    }

    public User findByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new PhoneNumberNotFoundException(AuthErrorCode.PHONE_NUMBER_NOT_FOUND));
    }
}
