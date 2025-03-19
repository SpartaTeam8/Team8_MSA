package com.sparta.user.application.service;

import com.sparta.user.domain.model.User;
import com.sparta.user.infrastructure.repository.UserRepository;
import com.sparta.user.presentation.dto.UserUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public String updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new IllegalArgumentException("사용자가 존재하지 않습니다.");

        User.update(user, userUpdateRequestDto);

        return "유저정보가 수정되었습니다.";
    }

    @Transactional
    public String deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new IllegalArgumentException("사용자가 존재하지 않습니다.");

        user.delete(LocalDateTime.now(), user.getUsername());

        return "유저정보가 삭제되었습니다.";
    }
}
