package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.testdatabuilder.domain.UserTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.UserDtoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class UserMapperTest {

    @Test
    @DisplayName("Must convert User to UserDTO")
    public void converUserToUserDto(){
        UserDto userDto = new UserDtoTestDataBuilder().build();
        User user = UserMapper.convertUserDtoToUser(userDto);
        assertEquals(user.getFirstName(),userDto.getFirstName());
        assertEquals(user.getLastName(),userDto.getLastName());
        assertEquals(user.getEmail(),userDto.getEmail());
        assertEquals(user.getPassword(),userDto.getPassword());
        assertEquals(user.getRole(),userDto.getRole());
        assertEquals(user.getWeeklyPercentage(),userDto.getWeeklyPercentage());
    }

    @Test
    @DisplayName("Must convert UserDto to User")
    public void converUserDtoToUser(){
        User user = new UserTestDataBuilder().build();
        UserDto userDto = UserMapper.convertUserToUserDto(user);
        assertEquals(user.getId(),userDto.getId());
        assertEquals(user.getFirstName(),userDto.getFirstName());
        assertEquals(user.getLastName(),userDto.getLastName());
        assertEquals(user.getEmail(),userDto.getEmail());
        assertEquals(user.getPassword(),userDto.getPassword());
        assertEquals(user.getRole(),userDto.getRole());
        assertEquals(user.getWeeklyPercentage(),userDto.getWeeklyPercentage());
    }
}
