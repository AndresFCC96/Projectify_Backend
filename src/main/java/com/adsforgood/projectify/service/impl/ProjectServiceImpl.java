package com.adsforgood.projectify.service.impl;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.mapper.ProjectMapper;
import com.adsforgood.projectify.mapper.ProjectTimeMapper;
import com.adsforgood.projectify.repository.ProjectRepository;
import com.adsforgood.projectify.service.ProjectService;
import com.adsforgood.projectify.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAllProjects() throws Exception {
        List<Project> projectList = projectRepository.findAll();
        if (projectList.isEmpty()) {
            throw new ExceptionManager.EmptyListException("Projects");
        } else {
            return projectList;
        }
    }

    @Override
    public Project findProjectById(String projectId) throws Exception {
        if (!Utils.isNumeric(projectId)) {
            throw new ExceptionManager.EmptyFieldException(projectId);
        } else {
            Optional<Project> project = projectRepository.findById(Long.valueOf(projectId));
            if (project.isEmpty()) {
                throw new ExceptionManager.NoEntityFoundWithValue("Project", projectId);
            } else {
                return project.get();
            }
        }
    }

    @Override
    public Project findProjectByName(String name) throws Exception {
        if (! Utils.isAString(name)) {
            throw new ExceptionManager.EmptyFieldException("Name");
        } else {
            Optional<Project> project = projectRepository.findByName(name);
            if (project.isEmpty()) {
                throw new ExceptionManager.NoEntityFoundWithValue("Project", name);
            } else {
                return project.get();
            }
        }
    }

    @Override
    public Project saveProject(ProjectDto projectDto) throws Exception {
        if (!Utils.isAnObject(projectDto)) {
            throw new ExceptionManager.NotAValidEntity(ProjectDto.class.getSimpleName());
        } else {
            validateName(projectDto.getName());
            validateDescription(projectDto.getDescription());
            Project project = ProjectMapper.convertProjectDtoToProject(projectDto);
            return projectRepository.save(project);
        }
    }

    @Override
    public Project modifyProject(ProjectDto projectDto) throws Exception {
        if (!Utils.isAnObject(projectDto)) {
            throw new ExceptionManager.NotAValidEntity(ProjectDto.class.getSimpleName());
        } else {
            validateId(projectDto.getId().toString());
            validateName(projectDto.getName());
            validateDescription(projectDto.getDescription());
            Optional<Project> projectOptional = projectRepository.findById(projectDto.getId());
            if(projectOptional.isEmpty()){
                throw new ExceptionManager.NoEntityFoundWithValue("Project", projectDto.getId().toString());
            }else {
                Project projectAux = projectOptional.get();
                projectAux = ProjectMapper.convertProjectDtoToProject(projectDto);
                projectAux.setId(projectDto.getId());
                return projectRepository.save(projectAux);

            }
        }
    }

    @Override
    public void deleteProject(Long projectId) throws Exception {
        validateId(projectId.toString());
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()){
            throw new ExceptionManager.NoEntityFoundWithValue("Project", projectId.toString());
        }else {
            projectRepository.delete(project.get());
        }
    }

    public boolean validateId(String  id) throws Exception{
        if (!Utils.isNumeric(id)) {
            throw new ExceptionManager.InvalidValueException(id);
        }else{
            return true;
        }
    }
    public boolean validateName(String name) throws Exception{
        if (name.isEmpty()) {
            throw new ExceptionManager.EmptyFieldException("Name");
        }else{
            return true;
        }
    }

    public boolean validateDescription(String description) {
        if (description.isEmpty()) {
            throw new ExceptionManager.EmptyFieldException("Description");
        }else{
            return true;
        }
    }

}