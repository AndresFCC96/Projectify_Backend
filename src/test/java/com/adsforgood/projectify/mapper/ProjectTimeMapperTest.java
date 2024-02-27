package com.adsforgood.projectify.mapper;

import com.adsforgood.projectify.domain.ProjectTime;
import com.adsforgood.projectify.dto.ProjectTimeDto;
import com.adsforgood.projectify.testdatabuilder.domain.ProjectTimeTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectTimeDtoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProjectTimeMapperTest {

    @Test
    @DisplayName("Must convert projectTimeDto to projectTime")
    void mustConvertProjectTimeDtoToProjectTimeTest() throws ParseException {
        ProjectTimeDto projectTimeDto = new ProjectTimeDtoTestDataBuilder().build();
        ProjectTime projectTime = ProjectTimeMapper.convertProjectTimeDtotoProjectTime(projectTimeDto);
        assertEquals(projectTime.getId(), projectTimeDto.getId());
        assertEquals(projectTime.getProjectId(), projectTimeDto.getProjectId());
        assertEquals(projectTime.getUserId(), projectTimeDto.getUserId());
        assertEquals(projectTime.getActivitieName(), projectTimeDto.getActivitieName());
        assertEquals(projectTime.getWeekTimeReport(), projectTimeDto.getWeekTimeReport());
        assertEquals(projectTime.getWeekDateReport(), projectTimeDto.getWeekDateReport());
        assertEquals(projectTime.getPercentageReport(), projectTimeDto.getPercentageReport());
        assertEquals(projectTime.getReportedAt(), projectTimeDto.getReportedAt());
    }

    @Test
    @DisplayName("Must convert projectTime to projectTimeDto")
    void mustConvertProjectTimeToProjectTimeDtoTest(){
        ProjectTime projectTime = new ProjectTimeTestDataBuilder().build();
        ProjectTimeDto projectTimeDto = ProjectTimeMapper.convertProjectTimetoProjectTimeDto(projectTime);
        assertEquals(projectTime.getId(), projectTimeDto.getId());
        assertEquals(projectTime.getProjectId(), projectTimeDto.getProjectId());
        assertEquals(projectTime.getUserId(), projectTimeDto.getUserId());
        assertEquals(projectTime.getActivitieName(), projectTimeDto.getActivitieName());
        assertEquals(projectTime.getWeekTimeReport(), projectTimeDto.getWeekTimeReport());
        assertEquals(projectTime.getWeekDateReport(), projectTimeDto.getWeekDateReport());
        assertEquals(projectTime.getPercentageReport(), projectTimeDto.getPercentageReport());
        assertEquals(projectTime.getReportedAt(), projectTimeDto.getReportedAt());
    }

}
