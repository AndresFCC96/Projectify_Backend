package com.adsforgood.projectify.service;

import com.adsforgood.projectify.domain.ProjectTime;
import com.adsforgood.projectify.dto.ProjectTimeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectTimeService {

    int dateToISO8601(String date) throws Exception;

    ProjectTime reportTime(ProjectTimeDto projectTimeDto) throws Exception;

    List<ProjectTime> getAllReportsPerUserPerProject(Long idProject, Long userId);

    boolean validateDate(String date) throws Exception;

    boolean validateId(String field, Long id) throws Exception;

    boolean validateActivitieName(String name) throws Exception;

    boolean validateTimReportOrPercentageReport(String field, int report) throws Exception;
}
