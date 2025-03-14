package com.sparta.userservice.controller;

import com.sparta.userservice.controller.dto.AuthRequestDto;
import com.sparta.userservice.controller.dto.AuthResponseDto;
import com.sparta.userservice.controller.dto.CommonResponse;
import com.sparta.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@RequestBody @Valid AuthRequestDto authRequestDto, BindingResult bindingResult) {

        // validation 예외처리
        raiseValidationException(bindingResult);

        // 예외가 없다면 회원가입시작
        AuthResponseDto authResponseDto = userService.signup(authRequestDto);

        return ResponseEntity
                .created(URI.create("/user/" + authResponseDto.getUserId()))
                .body(CommonResponse.OK(authResponseDto,"201"));
    }







    // bindingResult 에 에러가 존재한다면 에러를 던지는 함수
    private static void raiseValidationException(BindingResult bindingResult) {
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(!fieldErrors.isEmpty()){
            for(FieldError fieldError : fieldErrors){
                throw new IllegalArgumentException(fieldError.getDefaultMessage());
            }
        }
    }
}
