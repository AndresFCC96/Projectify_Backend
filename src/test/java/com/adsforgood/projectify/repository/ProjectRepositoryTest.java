package com.adsforgood.projectify.repository;

import com.adsforgood.projectify.domain.Project;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProjectRepositoryTest {

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Must save a project")
    void mustSaveAProject(){
        Project project = Project.builder().name("ProgrammingLanguaje").description("Unknown").build();
        projectRepository.save(project);
        Assertions.assertThat(project.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @DisplayName("Must return project by id")
    void getProjectByIdTest(){
        Project project = projectRepository.findById(1L).get();
        Assertions.assertThat(project.getId()).isEqualTo(1L);
    }
    @Test
    @Order(3)
    @DisplayName("Must return a list of projects")
    void getProjectsTest(){
        List<Project> projects = projectRepository.findAll();
        Assertions.assertThat(projects.size()).isGreaterThan(0);
    }


    @Test
    @Order(4)
    @DisplayName("Must return a project with a name")
    void mustReturnAProjectWithAName(){
        assertTrue(projectRepository.findByName("ProgrammingLanguaje").isPresent());
        assertEquals("ProgrammingLanguaje", projectRepository.findById(1L).get().getName());
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    @DisplayName("Must update a project")
    void mustUpdateAProject(){
        Project project = projectRepository.findById(1L).get();
        project.setName("Java 21");
        Project updatedProject = projectRepository.save(project);
        Assertions.assertThat(updatedProject.getName()).isEqualTo("Java 21");
    }

    @Test
    @Order(6)
    @Rollback(value = false)
    @DisplayName("Must delete a project")
    void mustDeleteAProject(){
        Project project = projectRepository.findById(1L).get();
        projectRepository.delete(project);
        Project project2 = null;
        Optional<Project> optionalProject = projectRepository.findByName("Python");
        if (optionalProject.isPresent()){
            project2 = optionalProject.get();
        }
        Assertions.assertThat(project2).isNull();
    }
}
