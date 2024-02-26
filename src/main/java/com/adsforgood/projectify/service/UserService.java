package com.adsforgood.projectify.service;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDetailsService userDetailsService();

    List<User> findAllUsers() throws Exception;

    User findByEmail(String email) throws Exception;

    User findById(Long id) throws Exception;

    User modifyUser(UserDto userDto) throws Exception;

    boolean validateName(String firstName, String lasstName) throws Exception;

    boolean validateEmail(String email) throws Exception;

    boolean validatePassword(String password) throws Exception;

    boolean validatePercentage(int percentage) throws Exception;

}
