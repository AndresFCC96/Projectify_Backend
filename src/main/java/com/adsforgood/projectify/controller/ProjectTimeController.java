package com.adsforgood.projectify.controller;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.dto.ProjectTimeDto;
import com.adsforgood.projectify.service.ProjectTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projectTime")
public class ProjectTimeController {

    @Autowired
    private ProjectTimeService projectTimeService;

    @GetMapping("/convertweektoISO8601")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> convertWeekToISO8601(@RequestParam("date") String date) throws Exception {
        try {
            return ResponseEntity.ok().body(projectTimeService.dateToISO8601(date));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/reportweekprojecttime")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> reportWeekProjectTime(@RequestBody ProjectTimeDto projectTimeDto) throws Exception {
        try {
            projectTimeService.reportTime(projectTimeDto);
            return ResponseEntity.ok(ExceptionManager.ENTITY_SUCCESFULLYSAVED);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/userreportsperproject")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> findHistoricalReportsPerUserPerProject(@RequestBody ProjectTimeDto projectTimeDto) throws Exception {
        try {
            return ResponseEntity.ok().body(
                    projectTimeService.getAllReportsPerUserPerProject(projectTimeDto.getProjectId(), projectTimeDto.getUserId()));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
