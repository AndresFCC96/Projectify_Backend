package com.adsforgood.projectify.service;

import com.adsforgood.projectify.dao.request.SignUpRequest;
import com.adsforgood.projectify.dao.request.SignInRequest;
import com.adsforgood.projectify.dao.response.JwtAuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SignInRequest request);
}
