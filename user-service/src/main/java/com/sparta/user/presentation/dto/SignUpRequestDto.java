package com.sparta.user.presentation.dto;


import com.sparta.user.domain.enumtype.Role;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    @NotBlank(message = "username은 필수 입력값입니다.")
    @Pattern(regexp = "^[a-z0-9]+$")
    private String username;

    @NotBlank(message = "email은 필수 입력값입니다.")
    @Email
    private String email;

    @NotBlank(message = "password는 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9_!#$%&'*+/=?`{|}~^.-]+$")
    private String password;

    @NotNull(message = "role는 필수 입력값입니다.")
    private Role role;

    private String adminToken;
}
