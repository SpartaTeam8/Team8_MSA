package com.sparta.user.presentation.controller;

import com.sparta.user.application.service.UserService;
import com.sparta.user.presentation.dto.CommonResponse;
import com.sparta.user.presentation.dto.UserGetResponseDto;
import com.sparta.user.presentation.dto.UserUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateRequestDto){
        String message = userService.updateUser(id, userUpdateRequestDto);

        return ResponseEntity.ok(CommonResponse.OK(message,"200"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        String message = userService.deleteUser(id);

        return ResponseEntity.ok(CommonResponse.OK(message,"200"));
    }

    @GetMapping("/{id}")
    public ResponseEntity getUsers(@PathVariable Long id){
        UserGetResponseDto userGetResponseDto = userService.getUser(id);

        return ResponseEntity.ok(CommonResponse.OK(userGetResponseDto,"200"));
    }
}
