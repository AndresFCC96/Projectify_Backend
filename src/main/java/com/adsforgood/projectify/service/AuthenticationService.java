package com.adsforgood.projectify.service;

import com.adsforgood.projectify.dto.request.SignUpRequest;
import com.adsforgood.projectify.dto.request.SignInRequest;
import com.adsforgood.projectify.dto.response.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
