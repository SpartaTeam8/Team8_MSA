package com.sparta.user.application.service;

import com.sparta.user.domain.enumtype.Role;
import com.sparta.user.domain.model.User;
import com.sparta.user.infrastructure.repository.UserRepository;
import com.sparta.user.presentation.dto.SignUpRequestDto;
import com.sparta.user.presentation.dto.SignUpResponseDto;
import com.sparta.user.presentation.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public SignUpResponseDto signUp(@Valid SignUpRequestDto signUpRequestDto) {
        String username = signUpRequestDto.getUsername();
        String password = passwordEncoder.encode(signUpRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = signUpRequestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인 ( 관리자 권한은 Token으로 확인 )
        Role role = signUpRequestDto.getRole();
        if (role.equals(Role.MASTER)) {
            if (!"ax1".equals(signUpRequestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
       }

        // 사용자 등록
        User user = User.createUser(username, email, password, role);
        userRepository.save(user);

        return SignUpResponseDto
                .builder()
                .userId(user.getUserId())
                .build();
    }
}
