package com.sparta.user.presentation.dto;


import lombok.Getter;

@Getter
public class UserGetResponseDto {

    Long userId;
    String username;
    String email;

    public UserGetResponseDto(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
