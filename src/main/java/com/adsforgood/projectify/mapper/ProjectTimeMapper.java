package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.ProjectTime;
import com.adsforgood.projectify.dto.ProjectTimeDto;
import com.adsforgood.projectify.utility.Utils;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ProjectTimeMapper {

    public static ProjectTime convertProjectTimeDtotoProjectTime(ProjectTimeDto projectTimeDto) throws ParseException {
        return ProjectTime.builder()
                .projectId(projectTimeDto.getProjectId())
                .userId(projectTimeDto.getUserId())
                .activitieName(projectTimeDto.getActivitieName())
                .weekTimeReport(projectTimeDto.getWeekTimeReport())
                .weekDateReport(Utils.stringDateToDate(projectTimeDto.getWeekDateReport()))
                .percentageReport(projectTimeDto.getPercentageReport())
                .reportedAt(projectTimeDto.getReportedAt())
                .build();
    }

    public static ProjectTimeDto convertProjectTimetoProjectTimeDto(ProjectTime projectTime){
        return ProjectTimeDto.builder()
                .id(projectTime.getId())
                .projectId(projectTime.getProjectId())
                .userId(projectTime.getUserId())
                .activitieName(projectTime.getActivitieName())
                .weekTimeReport(projectTime.getWeekTimeReport())
                .weekDateReport(projectTime.getWeekDateReport().toString())
                .percentageReport(projectTime.getPercentageReport())
                .reportedAt(projectTime.getReportedAt())
                .build();

    }
}
