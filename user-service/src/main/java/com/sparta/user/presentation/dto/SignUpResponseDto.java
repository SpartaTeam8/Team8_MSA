package com.sparta.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class SignUpResponseDto {
    Long userId;
}
