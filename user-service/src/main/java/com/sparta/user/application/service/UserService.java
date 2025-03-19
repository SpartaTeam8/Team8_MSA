package com.sparta.user.application.service;

import com.sparta.user.domain.model.User;
import com.sparta.user.infrastructure.repository.UserRepository;
import com.sparta.user.presentation.dto.CommonResponse;
import com.sparta.user.presentation.dto.UserUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String updateUser(Long id, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(id).orElse(null);
        if(user == null) throw new IllegalArgumentException("사용자가 존재하지 않습니다.");

        User.update(user, userUpdateRequestDto);

        return "유저정보가 수정되었습니다.";
    }
}
