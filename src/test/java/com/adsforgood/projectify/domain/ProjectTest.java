package com.adsforgood.projectify.domain;

import com.adsforgood.projectify.testdatabuilder.domain.ProjectTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProjectTest {

    @Test
    @DisplayName("Project should be created correctly")
    void theProjectMustBeCreatedSucesfully(){
        //Arrange
        String name = "Instagram";
        String description = "A social network where people can upload photos";
        //Act
        Project project = new ProjectTestDataBuilder()
                .projectWithId(1L)
                .build();
        //Assert
        assertEquals(1, project.getId());
        assertEquals(name, project.getName());
        assertEquals(description, project.getDescription());
    }

    @Test
    @DisplayName("Project shouldn't be created correctly")
    void theProjectMustNotBeCreatedSucesfully(){
        //Act
        Project project = new ProjectTestDataBuilder()
                .projectWithId(null)
                .projectWithName(null)
                .projectWithDescripction(null)
                .build();
        //Assert
        assertNull(project.getId());
        assertNull(project.getName());
        assertNull(project.getDescription());
    }

    @Test
    @DisplayName("Must fail without Id")
    void mustFailWithoutId(){
        //Arrange
        Project projectTestDataBuilder = new ProjectTestDataBuilder()
                .projectWithId(null)
                .build();
        Project ashawanda = new Project(null, "sfa", "Ssgsaga");
        //Act-Assert
        assertNull(ashawanda.getId());
    }

    @Test
    @DisplayName("Must fail without a name")
    void mustFailWithoutName(){
        //Arrange
        Project projectTestDataBuilder = new ProjectTestDataBuilder()
                .projectWithId(1L)
                .projectWithName(null)
                .build();
        //Act-Assert
        assertNull(projectTestDataBuilder.getName());
    }

    @Test
    @DisplayName("Must fail without a description")
    void mustFailWithoutDescription(){
        //Arrange
        Project projectTestDataBuilder = new ProjectTestDataBuilder()
                .projectWithId(1L)
                .projectWithDescripction(null)
                .build();
        //Act-Assert
        assertNull(projectTestDataBuilder.getDescription());
    }
}
