package com.sparta.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignInResponseDto {

    private String token;

    public SignInResponseDto(String token) {
        this.token = token;
    }
}
