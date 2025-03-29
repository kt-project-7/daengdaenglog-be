package com.clover.service;

import com.clover.domain.User;
import com.clover.dto.request.SignInRequest;
import com.clover.dto.response.AuthResponse;
import com.clover.dto.response.SignInResponse;
import com.clover.exception.InvalidPasswordException;
import com.clover.exception.TokenNotValidException;
import com.clover.exception.errorcode.AuthErrorCode;
import com.clover.util.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String getTestToken(Long userId) {
        return jwtTokenProvider.createAccessToken(userService.findById(userId));
    }

    public SignInResponse signIn(
            HttpServletResponse response, SignInRequest request
    ) {
        User user = userService.findByPhoneNumber(request.phoneNumber().replaceAll("-", ""));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new InvalidPasswordException(AuthErrorCode.INVALID_PASSWORD);
        }

        // 토큰 생성
        String accessToken = jwtTokenProvider.createAccessToken(user);
        response.setHeader("Authorization", accessToken);

        return SignInResponse.of(user.getName(), user.getImageUri());
    }

    public AuthResponse validateToken(String token) {
        String parseToken = resolveToken(token);

        return AuthResponse.from(jwtTokenProvider.validateToken(parseToken));
    }

    private String resolveToken(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        throw new TokenNotValidException(AuthErrorCode.TOKEN_NOT_VALID);
    }
}