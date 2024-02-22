package com.adsforgood.projectify.service.impl;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.repository.UserRepository;
import com.adsforgood.projectify.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

}
