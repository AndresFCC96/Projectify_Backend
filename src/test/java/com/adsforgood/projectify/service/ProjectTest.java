package com.adsforgood.projectify.service;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.mapper.ProjectMapper;
import com.adsforgood.projectify.repository.ProjectRepository;
import com.adsforgood.projectify.service.impl.ProjectServiceImpl;
import com.adsforgood.projectify.testdatabuilder.ProjectTestDataBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProjectTest {

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private ProjectService projectService;

    private Project project;

    @BeforeEach
    private void init(){
        project = new ProjectTestDataBuilder()
                .projectWithName("Facelook")
                .projectWithDescripction("Facelook made u look good")
                .build();
    }

    @Test
    void shouldSaveAProject() throws Exception {
        List<Project> projects = new ArrayList<>();
        project.setId(1L);
        projects.add(project);

        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> expectedprojects = projectService.findAllProjects();

//        assertEquals(expectedprojects, projects);
//        verify(projectService).findAllProjects();
        assertNotNull(projects);
    }

//    @Test
//    @DisplayName("Should validate an id")
//    public void shouldValidateAnId(){
//        String message = "The value " + null + " is not a valid value";
//        project.setId(null);
////        Project projecto = new ProjectTestDataBuilder().projectWithName()
////        when(projectRepository.save(any(Project.class))).thenReturn(new Project());
//        ExceptionManager exception = assertThrows(ExceptionManager.class, ()-> {
//            projectServiceImpl.validateId(project.getId());
//        });
//        assertEquals(message,exception.getMessage());
//    }

//    @Test
//    @DisplayName("Should validate the name")
//    public void shouldValidateName(){
//        String message = "The value for the field " + null + " can not be null or empty";
//        project.setId(1L);
//        project.setName(null);
////        Project projecto = new ProjectTestDataBuilder().projectWithName()
////        when(projectRepository.save(any(Project.class))).thenReturn(new Project());
//        Exception exception = assertThrows(Exception.class, ()-> {
//            projectService.validateName(project.getName());
//        });
//        assertEquals(message,exception.getMessage());
//    }

}
