package com.adsforgood.projectify.testdatabuilder.domain;

import com.adsforgood.projectify.domain.ProjectTime;

public class ProjectTimeTestDataBuilder {

    private Long id;
    private Long projectId;
    private Long userId;
    private String activitieName;
    private int weekTimeReport;
    private String weekDateReport;
    private int percentageReport;
    private String reportedAt;

    public ProjectTimeTestDataBuilder(){
        projectId = 1L;
        userId = 1L;
        activitieName = "Coding";
        weekTimeReport = 4;
        weekDateReport = "27/02/2024";
        percentageReport = 40;
        reportedAt = "27/02/2024";
    }

    public ProjectTimeTestDataBuilder projectTimeWithId(Long id){
        this.id = id;
        return this;
    }

    public ProjectTimeTestDataBuilder projectTimeWithProjectId(Long projectId){
        this.projectId = projectId;
        return this;
    }

    public ProjectTimeTestDataBuilder projectTimeWithUserId(Long userId){
        this.projectId = userId;
        return this;
    }

    public ProjectTimeTestDataBuilder projectTimeWithActivitieName(String activitieName){
        this.activitieName = activitieName;
        return this;
    }

    public ProjectTimeTestDataBuilder projectTimeWithWeekTimeReport(int weekTimeReport){
        this.weekTimeReport = weekTimeReport;
        return this;
    }

    public ProjectTimeTestDataBuilder projectTimeWithWeekDateReport(String weekDateReport){
        this.weekDateReport = weekDateReport;
        return this;
    }

    public ProjectTimeTestDataBuilder projectTimeWithPercentageReport(int percentageReport){
        this.percentageReport = percentageReport;
        return this;
    }

    public ProjectTimeTestDataBuilder projectTimeWithReportedAt(String reportedAt){
        this.reportedAt = reportedAt;
        return this;
    }

    public ProjectTime build()
    {
        return new ProjectTime(id,
                projectId,
                userId,
                activitieName,
                weekTimeReport,
                weekDateReport,
                percentageReport,
                reportedAt);
    };
}
