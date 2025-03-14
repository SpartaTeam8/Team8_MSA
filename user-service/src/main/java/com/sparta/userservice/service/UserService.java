package com.sparta.userservice.service;

import com.sparta.userservice.controller.dto.AuthRequestDto;
import com.sparta.userservice.controller.dto.AuthResponseDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public AuthResponseDto signup(@Valid AuthRequestDto authRequestDto) {

    }
}
