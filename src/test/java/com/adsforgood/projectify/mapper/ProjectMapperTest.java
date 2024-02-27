package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.testdatabuilder.domain.ProjectTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectDtoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ProjectMapperTest {

    @Test
    @DisplayName("Must convert projectDto to project")
    void mustConvertProjectDtoToProjectTest(){
        ProjectDto projectDto = new ProjectDtoTestDataBuilder().build();
        Project project = ProjectMapper.convertProjectDtoToProject(projectDto);
        assertEquals(project.getName(), projectDto.getName());
        assertEquals(project.getDescription(), projectDto.getDescription());
    }

    @Test
    @DisplayName("Must convert project to projectDto")
    void mustConvertProjectToProjectDtoTest(){
        Project project = new ProjectTestDataBuilder().build();
        ProjectDto projectDto = ProjectMapper.convertProjectToProjectDto(project);
        assertEquals(project.getId(), projectDto.getId());
        assertEquals(project.getName(), projectDto.getName());
        assertEquals(project.getDescription(), projectDto.getDescription());
    }

}
