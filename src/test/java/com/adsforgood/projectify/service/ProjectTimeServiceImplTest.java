package com.adsforgood.projectify.service;

import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.domain.Role;
import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.dto.ProjectTimeDto;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.repository.ProjectRepository;
import com.adsforgood.projectify.repository.ProjectTimeRepository;
import com.adsforgood.projectify.repository.UserRepository;
import com.adsforgood.projectify.service.impl.ProjectTimeServiceImpl;
import com.adsforgood.projectify.testdatabuilder.domain.UserTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectDtoTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectTimeDtoTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.UserDtoTestDataBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProjectTimeServiceImplTest {

    @Mock
    private ProjectTimeRepository projectTimeRepository;
    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProjectTimeService projectTimeService;
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @InjectMocks
    private ProjectTimeServiceImpl projectTimeServiceImpl;

    private User user;
    private UserDto userDto;


    @BeforeEach
    private void init(){
        user = new UserTestDataBuilder()
                .userWithId(1L)
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

        userRepository.saveAndFlush(user);
    }
    @Test
    @DisplayName("Must return the week day")
    void mustReturnTheWeekDay() throws Exception {
        String date = "26/02/2024";
        int weekDayFrom26 = 9;
        int returningWeek = projectTimeServiceImpl.dateToISO8601(date);
        assertEquals(weekDayFrom26,returningWeek);
    }

    @Test
    @DisplayName("Must not return the week day")
    void mustNotReturnTheWeekDay() throws Exception {
        String date = "9999/9999/9999";
        String message = "The given date is invalid";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectTimeServiceImpl.dateToISO8601(date); });
        assertEquals(message,exception.getMessage());
    }

    @Test
    @DisplayName("Throw exception on projectTimeProject when projectDto is null")
    void mustThrowExceptionIfProjectTimeDtoIsNull() {
        //Arrange
        ProjectTimeDto projectTimeDto = null;
        String entity = "ProjectTimeDto";
        String message = entity + " is not valid or must be null";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectTimeServiceImpl.reportTime(projectTimeDto);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

//    @Disabled
//    @Test
//    @DisplayName("Should projectTime a report")
//    void shouldSaveAReport() throws Exception {
//        //Arrange
//        ProjectTimeDto projectTime1 = new ProjectTimeDtoTestDataBuilder().build();
//        Optional<User> user1 = Optional.of(User.builder().id(3L).build());
//        Optional<Project> project = Optional.of(Project.builder().id(3L).build());
//        //Act
//        Mockito.when(projectRepository.findById(anyLong())).thenReturn(project);
//        Mockito.when(userRepository.findById(anyLong())).thenReturn(user1);
//        projectTimeServiceImpl.reportTime(projectTime1);
//        //Assert
//        Mockito.verify(projectTimeRepository, Mockito.times(1)).save(any());
//    }
}