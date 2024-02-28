package com.adsforgood.projectify.controller;

import com.adsforgood.projectify.domain.ProjectTime;
import com.adsforgood.projectify.dto.ProjectDto;
import com.adsforgood.projectify.dto.ProjectTimeDto;
import com.adsforgood.projectify.mapper.ProjectTimeMapper;
import com.adsforgood.projectify.service.ProjectTimeService;
import com.adsforgood.projectify.testdatabuilder.domain.ProjectTimeTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectDtoTestDataBuilder;
import com.adsforgood.projectify.testdatabuilder.dto.ProjectTimeDtoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectTimeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ProjectTimeController projectTimeController;

    @Mock
    private ProjectTimeService projectTimeService;

    @Test
    @DisplayName("Must return the week in year")
    void convertDate() throws Exception {
        int week = 4;
        when(projectTimeService.dateToISO8601(anyString())).thenReturn(week);
        ProjectDto projectDto = new ProjectDtoTestDataBuilder().projectWithId(1L).projectWithName("Go").build();
        String date = "24/02/2024";
        int responseEntity = projectTimeService.dateToISO8601(date);
        assertThat(responseEntity).isEqualTo(4);
    }

    @Test
    @DisplayName("Should report")
    void shouldReport() throws Exception {
        ProjectTime projectTime = new ProjectTimeTestDataBuilder()
                .projectTimeWithProjectId(1L)
                .projectTimeWithUserId(1L)
                .projectTimeWithActivitieName("Testing")
                .projectTimeWithWeekTimeReport(3)
                .projectTimeWithWeekDateReport("15/01/2024")
                .projectTimeWithPercentageReport(60)
                .projectTimeWithReportedAt("27/02/2024")
                .build();
        when(projectTimeService.reportTime(any(ProjectTimeDto.class))).thenReturn(projectTime);
        ResponseEntity<?> responseEntity = projectTimeController.reportWeekProjectTime(ProjectTimeMapper.convertProjectTimetoProjectTimeDto(projectTime));
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Should not report")
    void shouldNotReport() throws Exception {
        ProjectTime projectTime = new ProjectTimeTestDataBuilder()
                .projectTimeWithProjectId(null)
                .projectTimeWithUserId(null)
                .projectTimeWithActivitieName(null)
                .projectTimeWithWeekTimeReport(3)
                .projectTimeWithWeekDateReport("15/01/2024")
                .projectTimeWithPercentageReport(60)
                .projectTimeWithReportedAt("27/02/2024")
                .build();
        when(projectTimeService.reportTime(any(ProjectTimeDto.class))).thenReturn(projectTime);
        ResponseEntity<?> responseEntity = projectTimeController.reportWeekProjectTime(null);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(500);
    }

    @Test
    @DisplayName("Should consult historical")
    void shouldConsultHistorical() throws Exception {
        List projectTimes = Collections.EMPTY_LIST;
        ProjectTimeDto projectTimeDto = new ProjectTimeDtoTestDataBuilder()
                .projectTimeWithProjectId(1L)
                .projectTimeWithUserId(1L)
                .projectTimeWithActivitieName(null)
                .projectTimeWithWeekTimeReport(3)
                .projectTimeWithWeekDateReport("15/01/2024")
                .projectTimeWithPercentageReport(60)
                .projectTimeWithReportedAt("27/02/2024")
                .build();
//        when(projectTimeService.getAllReportsPerUserPerProject(anyLong(), anyLong())).thenReturn(projectTimes);
        ResponseEntity<?> responseEntity = projectTimeController.reportWeekProjectTime(projectTimeDto);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }


}