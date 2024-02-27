package com.adsforgood.projectify.service;

import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {

    List<Project> findAllProjects() throws Exception;

    Project findProjectById(String projectId) throws Exception;

    Project findProjectByName(String name) throws Exception;

    Project saveProject(ProjectDto projectDto) throws Exception;

    Project modifyProject(ProjectDto projectDto) throws Exception;

    void deleteProject(Long projectId) throws Exception;

    boolean validateName(String name) throws Exception;
}
