package com.adsforgood.projectify.controller;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.service.ProjectService;
import com.adsforgood.projectify.testdatabuilder.domain.ProjectTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectDtoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @Test
    @DisplayName("Must list all projects")
    void listAllProjectsTest() throws Exception {
        ResponseEntity<?> responseEntity = projectController.listAllProjects();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Must no all projects")
    void listNoProjectsTest() throws Exception {
        doThrow(new ExceptionManager.EmptyListException("Projects"))
                .when(projectService)
                .findAllProjects();
        ResponseEntity<?> responseEntity = projectController.listAllProjects();
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }

    @Test
    @DisplayName("Should find project by name")
    void findProjectByname() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        when(projectService.findProjectByName(anyString())).thenReturn(project);
        ProjectDto projectDto = new ProjectDtoTestDataBuilder().projectWithId(1L).projectWithName("Go").build();
        ResponseEntity<?> responseEntity = projectController.findProjectByName(projectDto.getName());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    @DisplayName("Should find project by id")
    void findProjectById() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        when(projectService.findProjectById(anyString())).thenReturn(project);
        ResponseEntity<?> responseEntity = projectController.findProjectById(project.getId());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Must no find any project by id")
    void listNoProjectByIdTest() throws Exception {
        ResponseEntity<?> responseEntity = projectController.findProjectById(null);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }

    @Test
    @DisplayName("Should save a project")
    void shouldSaveAProject() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        when(projectService.saveProject(any(ProjectDto.class))).thenReturn(project);
        ProjectDto projectDto = new ProjectDtoTestDataBuilder().projectWithId(1L).projectWithName("Go").build();
        ResponseEntity<?> responseEntity = projectController.createNewProject(projectDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should not save a project")
    void shouldNotSaveAProject() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        when(projectService.saveProject(any(ProjectDto.class))).thenReturn(project);
        ProjectDto projectDto = null;
        ResponseEntity<?> responseEntity = projectController.createNewProject(projectDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }

    @Test
    @DisplayName("Should modify a project")
    void shouldModifyAProject() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        when(projectService.modifyProject(any(ProjectDto.class))).thenReturn(project);
        ProjectDto projectDto = new ProjectDtoTestDataBuilder().projectWithId(1L).projectWithName("Go").build();
        ResponseEntity<?> responseEntity = projectController.modifyAProject(projectDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should not modify a project")
    void shouldNotModifyAProject() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        when(projectService.modifyProject(any(ProjectDto.class))).thenReturn(project);
        ProjectDto projectDto = null;
        ResponseEntity<?> responseEntity = projectController.modifyAProject(projectDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }

    @Test
    @DisplayName("Should delete a project")
    void shouldDeleteAProject() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        doNothing().when(projectService).deleteProject(anyLong());
        ResponseEntity<?> responseEntity = projectController.deleteproject(project.getId());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should not delete a project")
    void shouldNotDeleteAProject() throws Exception {
        Project project = new ProjectTestDataBuilder().projectWithId(1L).projectWithName("K").projectWithDescripction("dot").build();
        doThrow(new ExceptionManager.NoEntityFoundWithValue("Project", null))
                .when(projectService)
                .deleteProject(isA(Long.class));
        ResponseEntity<?> responseEntity = projectController.deleteproject(project.getId());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }
}