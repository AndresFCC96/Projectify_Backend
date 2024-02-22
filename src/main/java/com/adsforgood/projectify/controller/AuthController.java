package com.adsforgood.projectify.controller;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserDataService userService;

//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody UserDto userDto){
//        return ResponseEntity.ok(new AuthResponse());
//    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) throws Exception {
        return ResponseEntity.ok(userService.saveUser(userDto));
    }
}
