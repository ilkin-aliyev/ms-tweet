package com.example.ms.tweet.service;

import com.example.ms.tweet.model.auth.SignInDto;
import com.example.ms.tweet.model.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenService tokenService;

    public TokenDto signIn(SignInDto dto) {
        // Send request to ms-user and get user id by username from ms-user.
        // If you don't write ms-user you can mock it response
        return tokenService.generateToken("1",50);
    }

    public void verifyToken(String accessToken){
        tokenService.validateToken(accessToken);
    }

    public TokenDto refreshTokens(String refreshToken){
        return tokenService.refreshTokens(refreshToken);
    }
}
