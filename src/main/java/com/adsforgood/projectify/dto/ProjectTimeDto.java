package com.adsforgood.projectify.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTimeDto {
    private Long id;
    private Long projectId;
    private Long userId;
    private Calendar weekTimeReport;
}
