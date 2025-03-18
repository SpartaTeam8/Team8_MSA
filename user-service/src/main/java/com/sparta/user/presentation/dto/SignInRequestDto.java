package com.sparta.user.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequestDto {
    @NotBlank(message = "username은 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]+$")
    private String username;

    @NotBlank(message = "password는 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9_!#$%&'*+/=?`{|}~^.-]+$")
    private String password;
}
