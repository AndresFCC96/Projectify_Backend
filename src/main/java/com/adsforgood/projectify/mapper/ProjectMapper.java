package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.utility.Utils;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public static Project convertProjectDtoToProject(ProjectDto projectDto){
        Project project = Project.builder()
                .name(projectDto.getName())
                .description(projectDto.getDescription())
                .build();
        return project;
    }

}
