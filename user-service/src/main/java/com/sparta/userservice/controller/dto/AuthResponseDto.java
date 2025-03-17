package com.sparta.userservice.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class AuthResponseDto {
    Long userId;
}
