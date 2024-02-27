package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User convertUserDtoToUser(UserDto userDto){
        return User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .weeklyPercentage(userDto.getWeeklyPercentage())
                .build();
    }

    public static UserDto convertUserToUserDto(User user){
       return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .weeklyPercentage(user.getWeeklyPercentage())
                .build();
    }
}
