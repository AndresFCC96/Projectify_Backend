package com.adsforgood.projectify.service;

import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.domain.Role;
import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.repository.ProjectRepository;
import com.adsforgood.projectify.repository.UserRepository;
import com.adsforgood.projectify.service.impl.ProjectServiceImpl;
import com.adsforgood.projectify.service.impl.UserServiceImpl;
import com.adsforgood.projectify.testdatabuilder.domain.ProjectTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.domain.UserTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectDtoTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.UserDtoTestDataBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserService userService;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

    private User user;
    private UserDto userDto;

    @BeforeEach
    private void init(){
        user = new UserTestDataBuilder()
                .userWithfirstName("Jose")
                .userWithlastName("Carrillo")
                .userWithEmail("joseCarrillo@gmail.com")
                .userWithPassword("jose123")
                .userWithRole(Role.USER)
                .userWithWeeklyPercentage(100)
                .build();

        userDto = new UserDtoTestDataBuilder()
                .userWithfirstName("Camilo")
                .userWithlastName("Carrillo")
                .userWithEmail("camiloCarrillo@gmail.com")
                .userWithPassword("camilo123")
                .userWithRole(Role.USER)
                .userWithWeeklyPercentage(100)
                .build();
    }

    @Test
    @DisplayName("Should not validate name on the method validateName")
    void shouldNotValidateName() {
        //Arrange
        String firstName = "125";
        String lastName = "521";
        String value = "First or last name";
        String message = "The value " + value + " is not a valid value";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.validateName(firstName, lastName);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Validate the name on validateName")
    void shouldValidateName() throws Exception {
        //Arrange
        String firstName = "Jose";
        String lastName = "Mazuera";
        boolean value = userServiceImpl.validateName(firstName, lastName);
        //Act-Assert
        Assertions.assertTrue(value);
    }

    @Test
    @DisplayName("Should not validate email on the method validateEmail")
    void shouldNotValidateEmail() {
        //Arrange
        String email = "125";
        String value = "Email";
        String message = "The value " + value + " is not a valid value";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.validateEmail(email);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Validate the name on validateEmail")
    void shouldValidateEmail() throws Exception {
        //Arrange
        String email = "joseMazuera@gmail.com";
        boolean value = userServiceImpl.validateEmail(email);
        //Act-Assert
        Assertions.assertTrue(value);
    }

    @Test
    @DisplayName("Should not validate password on the method validatePassword")
    void shouldNotValidatePassword() {
        //Arrange
        String password = "125";
        String message = "Invalid password format";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.validatePassword(password);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Validate the password on validatePassword")
    void shouldValidatePassword() throws Exception {
        //Arrange
        String password = "Geeks@portal20";
        boolean value = userServiceImpl.validatePassword(password);
        //Act-Assert
        Assertions.assertTrue(value);
    }

    @Test
    @DisplayName("Should not validate percentage on the method validatePercentage")
    void shouldNotValidatePercentage() {
        //Arrange
        int min = 1;
        int max = 100;
        int percentage = 2000;
        String field = "Percentage";
        String message = field + " must be between " +  min + " and " + max;
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.validatePercentage(percentage);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should validate percentage on the method validatePercentage")
    void shouldValidatePercentage() throws Exception {
        //Arrange
        int min = 1;
        int max = 100;
        int percentage = 60;
        boolean value = userServiceImpl.validatePercentage(percentage);
        //Act-Assert
        Assertions.assertTrue(value);
    }
//    @Test
//    @DisplayName("Validate the id on validateId")
//    void shouldValidateId() throws Exception {
//        //Arrange
//        String id = "1";
//        boolean value = projectServiceImpl.validateId(id);
//        //Act-Assert
//        Assertions.assertTrue(value);
//    }
//

    @Test
    @DisplayName("Should return no users")
    void shouldReturnNoUsers() throws Exception {
        //Arrange
        String field = "User";
        String message = "No elements found on " + field + " list";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.findAllUsers();
        });
        //Assert
        Assert.assertEquals(exception.getMessage(), message);
    }

    @Test
    @DisplayName("Should return all the users")
    void shouldReturnAllProjects() throws Exception {
        //Arrange
        List<User> list = new ArrayList<User>();
        User user1 = User.builder().id(2L).firstName("Ruby").build();
        User user2 = User.builder().id(3L).firstName("Zafiro").build();
        User user3 = User.builder().id(4L).firstName("Escarlata").build();
        list.add(user1);
        list.add(user2);
        list.add(user3);
        //Act
        Mockito.when(userRepository.findAll()).thenReturn(list);
        List<User> emptyList = userServiceImpl.findAllUsers();
        //Assert
        Assert.assertEquals(3, emptyList.size());
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("UserId equals null on method findById")
    void userIdEqualsNullOnMethodFindById() {
        //Arrange
        String id = "s";
        String field = "Id";
        String message = "Id must not be null";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.findById(id);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Must return user with id on findById")
    void mustReturnUserWithIdOnFindById() throws Exception {
        //Arrange
        User user1 = User.builder().id(3L).build();
        Optional<User> user2 = Optional.of(user1);
        //Act
        Mockito.when(userRepository.findById(anyLong())).thenReturn(user2);
        User userFound = userServiceImpl.findById(user1.getId().toString());
        //Assert
        Assert.assertEquals(user1, userFound );
    }

    @Test
    @DisplayName("Email equals null on method findByEmail")
    void emailEqualsNullOnMethodFindById() {
        //Arrange
        String email = "s";
        String message = "The value " + "email" + " is not a valid value";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.findByEmail(email);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Must return user with email on findByEmail")
    void mustReturnUserWithIdOnFindByEmail() throws Exception {
        //Arrange
        User user1 = User.builder().id(3L).email("joaofelix@gmail.com").build();
        Optional<User> user2 = Optional.of(user1);
        //Act
        Mockito.when(userRepository.findByEmail(anyString())).thenReturn(user2);
        User userFound = userServiceImpl.findByEmail(user1.getEmail().toString());
        //Assert
        Assert.assertEquals(user1, userFound );
    }
//    @Test
//    @DisplayName("Should save an user")
//    void shouldSaveAProject() throws Exception {
//        //Arrange
//        UserDto userDto1 = new UserDtoTestDataBuilder().build();
//        //Act
//        userServiceImpl.sa(project1);
//        //Assert
//        Mockito.verify(projectRepository, Mockito.times(1)).save(any());
//    }
//
//    @Test
//    @DisplayName("Throw exception on saveProject when projectDto is null")
//    void mustThrowExceptionIfProjectDtoIsNull() {
//        //Arrange
//        ProjectDto projectDto1 = null;
//        String entity = "ProjectDto";
//        String message = entity + " is not valid or must be null";
//        //Act
//        Exception exception = Assert.assertThrows(Exception.class, () -> {
//            projectServiceImpl.saveProject(projectDto1);
//        });
//        //Assert
//        Assertions.assertEquals(message, exception.getMessage());
//    }
//
    @Test
    @DisplayName("Should modify an user")
    void shouldModifyAnUser() throws Exception {
        //Arrange
        UserDto user1 = new UserDtoTestDataBuilder()
                .userWithId(1L)
                .userWithEmail("joaryryo@gmail.com")
                .userWithPassword("Geeks@portal20")
                .build();
        User user2 = new UserTestDataBuilder()
                .userWithId(1L)
                .userWithEmail("joafddgdo@gmail.com")
                .userWithPassword("Ge4ks@portal20")
                .build();
        Optional<User> userOptional = Optional.of(user2);
        user.setFirstName("Ramond");
        //Act

        userServiceImpl.modifyUser(user1);
        //Assert
        Mockito.verify(userRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Throw exception on modifyUser when usertDto is null")
    void mustThrowExceptionIfProjectDtoIsNullOnModifyProject() {
        //Arrange
        UserDto userDto1 = null;
        String entity = "UserDto";
        String message = entity + " is not valid or must be null";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            userServiceImpl.modifyUser(userDto1);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }
}
