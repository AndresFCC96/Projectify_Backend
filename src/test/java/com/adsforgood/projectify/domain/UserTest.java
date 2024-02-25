package com.adsforgood.projectify.domain;

import com.adsforgood.projectify.testdatabuilder.UserTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    @DisplayName("User should be created correctly")
    void theUserMustBeCreatedSucesfully(){
        //Arrange
        String firstName = "Pablo";
        String lastName = "Tejada";
        String email1 = "pablo@projectify.com";
        String password1 = "1212345";
        Role role = Role.ADMIN;
        //Act
        User user = new UserTestDataBuilder()
                .userWithId(1L)
                .build();
        //Assert
        assertEquals(1, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email1, user.getEmail());
        assertEquals(password1, user.getPassword());
        assertEquals(role, user.getRole());

    }

    @Test
    @DisplayName("Should return Id")
    void mustReturnId(){
        //Arrange
        Long idUser = 1L;
        //AAct
        User userTestDataBuilder = new UserTestDataBuilder().
                userWithId(1L)
                .build();
        //Assert
        assertEquals(idUser,userTestDataBuilder.getId());
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
    @DisplayName("Must fail without a first name")
    void mustFailWithoutFirstName(){
        //Arrange
        User userTestDataBuilder = new UserTestDataBuilder().
                userWithId(1L).
                userWithfirstName(null)
                .build();
        //Act-Assert
        assertNull(userTestDataBuilder.getFirstName());
    }

    @Test
    @DisplayName("Must fail without a last name")
    void mustFailWithoutLastName(){
        //Arrange
        User userTestDataBuilder = new UserTestDataBuilder().
                userWithId(1L).
                userWithlastName(null)
                .build();
        //Act-Assert
        assertNull(userTestDataBuilder.getLastName());
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

    @Test
    @DisplayName("Must fail without a role")
    void mustFailWithoutRole(){
        //Arrange
        User userTestDataBuilder = new UserTestDataBuilder().
                userWithId(1L).
                userWithRole(null)
                .build();
        //Act-Assert
        assertNull(userTestDataBuilder.getRole());
    }

    @Test
    @DisplayName("Must return the email")
    void mustReturnTheEmailFromLoggin(){
        //Act
        String email = "pablo@projectify.com";
        //Act
        User userTestDataBuilder = new UserTestDataBuilder().build();
        String emailUsernName = userTestDataBuilder.getUsername();
        //Assert
        assertEquals(email, emailUsernName);
    }

    @Test
    @DisplayName("Non expired accounts should be valid to access token")
    void nonExpiredAccountsShouldBeValidToAccessToken(){
        //Act
        User userTestDataBuilder = new UserTestDataBuilder().build();
        //Assert
        assertTrue(userTestDataBuilder.isAccountNonExpired());
    }

    @Test
    @DisplayName("New accounts shouldn't be locked")
    void newAccountsShouldntBeLocked(){
        //Act
        User userTestDataBuilder = new UserTestDataBuilder().build();
        //Assert
        assertTrue(userTestDataBuilder.isAccountNonLocked());
    }

    @Test
    @DisplayName("New accounts should be enable")
    void newAccountsShouldBeEnable(){
        //Act
        User userTestDataBuilder = new UserTestDataBuilder().build();
        //Assert
        assertTrue(userTestDataBuilder.isEnabled());
    }

    @Test
    @DisplayName("New accounts credentials should be valid")
    void newAccountsCredentialsShouldBeValid(){
        //Act
        User userTestDataBuilder = new UserTestDataBuilder().build();
        //Assert
        assertTrue(userTestDataBuilder.isCredentialsNonExpired());
    }

    @Test
    @DisplayName("Must Return User privileges")
    void mustReturnUserPrivileges(){
        //Arrange
        String admin = "ADMIN";
        //Act
        User userTestDataBuilder = new UserTestDataBuilder().build();
        String role =  userTestDataBuilder.getAuthorities().toString();
        role = role.replace("[", "").replace("]", "");
        //Assert
        assertNotNull(userTestDataBuilder.getAuthorities());
        assertEquals(admin, role);
    }
}
