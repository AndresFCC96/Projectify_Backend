package com.adsforgood.projectify.controller;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.service.ProjectService;
import com.adsforgood.projectify.utility.Utils;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/projects")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listAllProjects() throws Exception {
        try {
            return ResponseEntity.ok().body(projectService.findAllProjects());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/findprojectbyid")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findProjectById(@RequestParam("id") Long id) throws Exception {
        try {
            return ResponseEntity.ok().body(projectService.findProjectById(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/findprojectbyname")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findProjectByName(@RequestParam("name") String name) throws Exception {
        try {
            return ResponseEntity.ok().body(projectService.findProjectByName(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/newproject")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createNewProject(@RequestBody ProjectDto projectDto) throws Exception {
        try {
            projectService.saveProject(projectDto);
            return ResponseEntity.ok(ExceptionManager.ENTITY_SUCCESFULLYSAVED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/modifyproject")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> modifyAProject(@RequestBody ProjectDto projectDto) throws Exception {
        try {
            projectService.modifyProject(projectDto);
            return ResponseEntity.ok(ExceptionManager.ENTITY_SUCCESFULLYMODIFIED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteuser")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteuser(@RequestParam("id") Long projectId){
        try {
            projectService.deleteProject(projectId);
            return ResponseEntity.ok(ExceptionManager.ENTITY_SUCCESFULLYDELETED);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
