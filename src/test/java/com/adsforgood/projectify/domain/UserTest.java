package com.adsforgood.projectify.domain;

import com.adsforgood.projectify.testdatabuilder.UserTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserTest {

    @Test
    @DisplayName("User should be created correctly")
    void theUserMustBeCreatedSucesfully(){
        //Arrange
        String email1 = "pablo@projectify.com";
        String password1 = "1212345";
        //Act
        User user = new UserTestDataBuilder()
                .userWithId(1L)
                .build();
        //Assert
        assertEquals(1, user.getId());
        assertEquals(email1, user.getEmail());
        assertEquals(password1, user.getPassword());
    }

    @Test
    @DisplayName("Must fail without Id")
    void mustFailWithoutId(){
        //Arrange
        User userTestDataBuilder = new UserTestDataBuilder().
                userWithId(null)
                .build();
        //Act-Assert
        assertNull(userTestDataBuilder.getId());
    }

    @Test
    @DisplayName("Must fail without an email")
    void mustFailWithoutEmail(){
        //Arrange
        User userTestDataBuilder = new UserTestDataBuilder().
                userWithId(1L).
                userWithEmail(null)
                .build();
        //Act-Assert
        assertNull(userTestDataBuilder.getEmail());
    }

    @Test
    @DisplayName("Must fail without a password")
    void mustFailWithoutPassword(){
        //Arrange
        User userTestDataBuilder = new UserTestDataBuilder().
                userWithId(1L).
                userWithPassword(null)
                .build();
        //Act-Assert
        assertNull(userTestDataBuilder.getPassword());
    }
}
