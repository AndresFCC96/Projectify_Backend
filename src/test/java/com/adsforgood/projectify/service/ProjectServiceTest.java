package com.adsforgood.projectify.service;

import com.adsforgood.projectify.Exception.ExceptionManager;

import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.mapper.ProjectMapper;
import com.adsforgood.projectify.repository.ProjectRepository;
import com.adsforgood.projectify.service.impl.ProjectServiceImpl;
import com.adsforgood.projectify.testdatabuilder.domain.ProjectTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectDtoTestDataBuilder;
import com.adsforgood.projectify.utility.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectService projectService;
    @InjectMocks
    private ProjectServiceImpl projectServiceImpl;

    private Project project;
    private ProjectDto projectDto;

    @BeforeEach
    private void init(){
        project = new ProjectTestDataBuilder()
                .projectWithName("Facelook")
                .projectWithDescripction("Facelook made u look good")
                .build();

        projectDto = new ProjectDtoTestDataBuilder()
                .projectWithName("Wazaaaa")
                .projectWithDescripction("Mega Wazaaaa")
                .build();
    }

    @Test
    @DisplayName("Should not validate name on the method validateName")
    void shouldNotValidateName() {
        //Arrange
        String string = "";
        String field = "Name";
        String message = "The value for the field " + field + " can not be null or empty";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.validateName(string);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Validate the name on validateName")
    void shouldValidateName() throws Exception {
        //Arrange
        String name = "Netflix";
        boolean value = projectServiceImpl.validateName(name);
        //Act-Assert
        Assertions.assertTrue(value);
    }

    @Test
    @DisplayName("Should not validate id on the method validateId")
    void shouldNotValidateIdWhenNull() {
        //Arrange
        String id = "s";
        String field = "Name";
        String message = "The value " + id + " is not a valid value";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.validateId(id);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("ProjectId equals null on method findById")
    void projectIdEqualsNullOnMethodFindById() {
        //Arrange
        String id = "s";
        String field = "Id";
        String message = "The value for the field " + id + " can not be null or empty";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.findProjectById(id);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Project is empty on findById")
    void projectIsEmptyOnFindById() {
        //Arrange
        String id = "1";
        String entity = "Project";
        String message = entity + " not found with the value " + id;
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.findProjectById(id);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Must return project with id on findById")
    void mustReturnProjectWithIdOnFindById() throws Exception {
        //Arrange
        Project project1 = Project.builder().id(3L).build();
        Optional<Project> project2 = Optional.of(project1);
        //Act
        Mockito.when(projectRepository.findById(anyLong())).thenReturn(project2);
        Project projectFound = projectServiceImpl.findProjectById(project1.getId().toString());
        //Assert
        Assert.assertEquals(project1, projectFound );
    }

    @Test
    @DisplayName("Should not validate name on the method findByName")
    void shouldNotValidateNameOnFindByName() {
        //Arrange
        String string = "";
        String field = "Name";
        String message = "The value for the field " + field + " can not be null or empty";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.findProjectByName(string);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Project is empty on findByName")
    void projectIsEmptyOnFindByName() {
        //Arrange
        String id = "1";
        String entity = "Project";
        String message = entity + " not found with the value " + id;
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.findProjectByName(id);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Must return project with name on findByName")
    void mustReturnProjectWithNameOnFindByName() throws Exception {
        //Arrange
        Project project1 = Project.builder().id(3L).name("Josh").build();
        Optional<Project> project2 = Optional.of(project1);
        //Act
        Mockito.when(projectRepository.findByName(anyString())).thenReturn(project2);
        Project projectFound = projectServiceImpl.findProjectByName(project1.getName());
        //Assert
        Assert.assertEquals(project1, projectFound );
    }

    @Test
    @DisplayName("Validate the id on validateId")
    void shouldValidateId() throws Exception {
        //Arrange
        String id = "1";
        boolean value = projectServiceImpl.validateId(id);
        //Act-Assert
        Assertions.assertTrue(value);
    }

    @Test
    @DisplayName("Should return no projects")
    void shouldReturnNoProjects() throws Exception {
        //Arrange
        String field = "Projects";
        String message = "No elements found on " + field + " list";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.findAllProjects();
        });
        //Assert
        Assert.assertEquals(exception.getMessage(), message);
    }

    @Test
    @DisplayName("Should return all the projects")
    void shouldReturnAllProjects() throws Exception {
        //Arrange
        List<Project> list = new ArrayList<Project>();
        Project project1 = Project.builder().id(2L).name("Ruby").build();
        Project project2 = Project.builder().id(3L).name("Zafiro").build();
        Project project3 = Project.builder().id(4L).name("Escarlata").build();
        list.add(project1);
        list.add(project2);
        list.add(project3);
        //Act
        Mockito.when(projectRepository.findAll()).thenReturn(list);
        List<Project> emptyList = projectServiceImpl.findAllProjects();
        //Assert
        Assert.assertEquals(3, emptyList.size());
        Mockito.verify(projectRepository, Mockito.times(1)).findAll();
    }

    @Test
    @DisplayName("Should save a project")
    void shouldSaveAProject() throws Exception {
        //Arrange
        ProjectDto project1 = new ProjectDtoTestDataBuilder().build();
        //Act
        projectServiceImpl.saveProject(project1);
        //Assert
        Mockito.verify(projectRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Throw exception on saveProject when projectDto is null")
    void mustThrowExceptionIfProjectDtoIsNull() {
        //Arrange
        ProjectDto projectDto1 = null;
        String entity = "ProjectDto";
        String message = entity + " is not valid or must be null";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.saveProject(projectDto1);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should modify a project")
    void shouldModifyAProject() throws Exception {
        //Arrange
        ProjectDto project1 = new ProjectDtoTestDataBuilder().projectWithId(1L).build();
        Project project2 = new ProjectTestDataBuilder().projectWithId(1L).build();
        Optional<Project> projectOptional = Optional.of(project2);
        project1.setName("Diamond");
        //Act
        Mockito.when(projectRepository.findById(anyLong())).thenReturn(projectOptional);
        projectServiceImpl.modifyProject(project1);
        //Assert
        Mockito.verify(projectRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("Throw exception on modifyProject when projectDto is null")
    void mustThrowExceptionIfProjectDtoIsNullOnModifyProject() {
        //Arrange
        ProjectDto projectDto1 = null;
        String entity = "ProjectDto";
        String message = entity + " is not valid or must be null";
        //Act
        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.modifyProject(projectDto1);
        });
        //Assert
        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    @DisplayName("Should not delete on empty optional")
    void shouldNotDeleteOnEmptyOptional() throws Exception {
        Project project1 = new ProjectTestDataBuilder().projectWithId(1L).build();
        String message = "Project not found with the value 1";

        Exception exception = Assert.assertThrows(Exception.class, () -> {
            projectServiceImpl.deleteProject(project1.getId());
        });

        Assert.assertEquals(message, exception.getMessage());
    }
}
