package com.sparta.user.presentation.controller;

import com.sparta.user.presentation.dto.*;
import com.sparta.user.application.service.AuthService;
import com.sparta.user.presentation.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        // 예외가 없다면 회원가입시작
        SignUpResponseDto signUpResponseDto = authService.signUp(signUpRequestDto);

        return ResponseEntity.ok(CommonResponse.OK(signUpResponseDto,"201"));
    }

    // 로그인
    @PostMapping("/sign-in")
    public ResponseEntity signIn(@RequestBody @Valid SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequestDto.getUsername(), signInRequestDto.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(CommonResponse.OK(jwtUtil.generateToken(userDetails.getUsername()), "200"));
    }

}
