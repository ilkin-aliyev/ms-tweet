package com.example.ms.tweet.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class TokenDto {

    private String accessToken;
    private String refreshToken;
}
