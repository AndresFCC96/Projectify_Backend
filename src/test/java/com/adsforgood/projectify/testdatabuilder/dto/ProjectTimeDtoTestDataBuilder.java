package com.adsforgood.projectify.testdatabuilder.dto;

import com.adsforgood.projectify.dto.ProjectTimeDto;

public class ProjectTimeDtoTestDataBuilder {

    private Long id;
    private Long projectId;
    private Long userId;
    private String activitieName;
    private int weekTimeReport;
    private String weekDateReport;
    private int percentageReport;
    private String reportedAt;

    public ProjectTimeDtoTestDataBuilder(){
        projectId = 1L;
        userId = 1L;
        activitieName = "Coding";
        weekTimeReport = 4;
        weekDateReport = "28/02/2024";
        percentageReport = 40;
        reportedAt = "28/02/2024";
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithId(Long id){
        this.id = id;
        return this;
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithProjectId(Long projectId){
        this.projectId = projectId;
        return this;
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithUserId(Long userId){
        this.projectId = userId;
        return this;
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithActivitieName(String activitieName){
        this.activitieName = activitieName;
        return this;
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithWeekTimeReport(int weekTimeReport){
        this.weekTimeReport = weekTimeReport;
        return this;
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithWeekDateReport(String weekDateReport){
        this.weekDateReport = weekDateReport;
        return this;
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithPercentageReport(int percentageReport){
        this.percentageReport = percentageReport;
        return this;
    }

    public ProjectTimeDtoTestDataBuilder projectTimeWithReportedAt(String reportedAt){
        this.reportedAt = reportedAt;
        return this;
    }

    public ProjectTimeDto build()
    {
        return new ProjectTimeDto(id,
                projectId,
                userId,
                activitieName,
                weekTimeReport,
                weekDateReport,
                percentageReport,
                reportedAt);
    };
}
