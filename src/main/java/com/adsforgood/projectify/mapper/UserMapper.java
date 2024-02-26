package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User convertUserDtoToUser(UserDto userDto){
        User user = User.builder()
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .weeklyPercentage(userDto.getWeeklyPercentage())
                .build();
        return user;
    }

    public static UserDto convertUserToUserDto(User user){
        UserDto userDto = UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .weeklyPercentage(user.getWeeklyPercentage())
                .build();
        return userDto;
    }
}
