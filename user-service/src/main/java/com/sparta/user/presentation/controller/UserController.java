package com.sparta.user.presentation.controller;

import com.sparta.user.application.service.UserService;
import com.sparta.user.presentation.dto.CommonResponse;
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

        return ResponseEntity.ok(CommonResponse.OK(message,"201"));
    }

}
