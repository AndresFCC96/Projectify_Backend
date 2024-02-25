package com.adsforgood.projectify.controller;

import com.adsforgood.projectify.config.JwtAuthenticationFilter;
import com.adsforgood.projectify.dao.request.SignInRequest;
import com.adsforgood.projectify.dao.request.SignUpRequest;
import com.adsforgood.projectify.domain.Role;
import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.service.AuthenticationService;
import com.adsforgood.projectify.service.JwtService;
import com.adsforgood.projectify.service.UserService;
import com.adsforgood.projectify.service.impl.AuthenticationServiceImpl;
import com.adsforgood.projectify.testdatabuilder.SignUpRequestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.SigninRequestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.UserTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
@ContextConfiguration
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JwtService jwtService;
    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private UserService userService;
    private SignUpRequest signUpRequest;
    private SignInRequest signInRequest;

    @BeforeEach
    private void init(){
        User user = new UserTestDataBuilder()
                .userWithfirstName("Camilo")
                .userWithlastName("Pachecho")
                .userWithEmail("capa@hotmail.com")
                .userWithPassword("capa123")
                .userWithRole(Role.ADMIN)
                .build();

        SignUpRequest signUpRequest = new SignUpRequestDataBuilder()
                .userWithfirstName("Jose")
                .userWithlastName("Almeira")
                .userWithEmail("joal@hotmail.com")
                .userWithPassword("joal799")
                .build();

        SignInRequest signInRequest = new SigninRequestDataBuilder()
                .userWithEmail("joal@hotmail.com")
                .userWithPassword("joal799")
                .build();
    }

    @Test
    @DisplayName("Must return token after a creating a new user sucesfully")
    public void AuthenticationController_Signup_ReturnToken() throws Exception{
        BDDMockito.given(authenticationService.signup(ArgumentMatchers.any()))
                .willAnswer(invocation ->
                        invocation.getArguments());
        ResultActions response = mockMvc.perform(post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(signUpRequest)));
        response.andExpect(MockMvcResultMatchers.status().isOk());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
