package com.sparta.user.application.service;

import com.sparta.user.domain.enumtype.Role;
import com.sparta.user.domain.model.User;
import com.sparta.user.infrastructure.repository.UserRepository;
import com.sparta.user.presentation.dto.AuthRequestDto;
import com.sparta.user.presentation.dto.AuthResponseDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    public AuthResponseDto signup(@Valid AuthRequestDto authRequestDto) {
        String username = authRequestDto.getUsername();
        String password = passwordEncoder.encode(authRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // email 중복확인
        String email = authRequestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인 ( 관리자 권한은 Token으로 확인 )
        Role role = authRequestDto.getRole();
        if (role.equals(Role.MASTER)) {
            if (!"ax1".equals(authRequestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
       }

        // 사용자 등록
        User user = User.createUser(username, email, password, role);
        userRepository.save(user);

        return AuthResponseDto
                .builder()
                .userId(user.getId())
                .build();
    }
}
