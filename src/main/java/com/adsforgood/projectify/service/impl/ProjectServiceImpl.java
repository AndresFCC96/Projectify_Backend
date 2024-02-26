package com.adsforgood.projectify.service.impl;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.domain.Project;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.mapper.ProjectMapper;
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
            throw new Exception("There's not projects to show");
        } else {
            return projectList;
        }
    }

    @Override
    public Project findProjectById(Long projectId) throws Exception {
        if (projectId == null) {
            throw new ExceptionManager.EmptyFieldException(projectId.toString());
        } else {
            Optional<Project> project = projectRepository.findById(projectId);
            if (project.isEmpty()) {
                throw new Exception("No user found with the id " + projectId);
            } else {
                return project.get();
            }
        }
    }

    @Override
    public Project findProjectByName(String name) throws Exception {
        if (! Utils.isAString(name)) {
            throw new ExceptionManager.EmptyFieldException(name);
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
            System.out.println(Utils.isAnObject(projectDto));
            throw new ExceptionManager.NotAValidEntity(Project.class.getSimpleName());
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
            throw new ExceptionManager.NotAValidEntity(Project.class.getSimpleName());
        } else {
            validateId(projectDto.getId());
            validateName(projectDto.getName());
            validateDescription(projectDto.getDescription());
            Project project = ProjectMapper.convertProjectDtoToProject(projectDto);
            project.setId(projectDto.getId());
            return projectRepository.save(project);
        }
    }

    @Override
    public void deleteProject(Long projectId) throws Exception {
        validateId(projectId);
        Optional<Project> project = projectRepository.findById(projectId);
        if (project.isEmpty()){
            throw new ExceptionManager.NoEntityFoundWithValue("Project", projectId.toString());
        }else {
            projectRepository.delete(project.get());
        }
    }

    public boolean validateId(Long id) throws Exception{
        if (!Utils.isNumeric(id.toString()) || id == null) {
            throw new ExceptionManager.InvalidValueException(id.toString());
        }else{
            return true;
        }
    }
    public boolean validateName(String name) throws Exception{
        if (name.equals(null)) {
            throw new ExceptionManager.EmptyFieldException("Name");
        }else{
            return true;
        }
    }

    public boolean validateDescription(String description) {
        if (!Utils.isAString(description) || description.isEmpty()) {
            throw new ExceptionManager.EmptyFieldException("Description");
        }else{
            return true;
        }
    }

}