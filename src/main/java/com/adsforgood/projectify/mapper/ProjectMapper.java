package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public static Project convertProjectDtoToProject(ProjectDto projectDto){
        return Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .build();
    }

    public static ProjectDto convertProjectToProjectDto(Project project){
        return ProjectDto.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .build();
    }

}
