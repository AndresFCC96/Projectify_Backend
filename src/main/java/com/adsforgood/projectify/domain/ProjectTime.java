package com.adsforgood.projectify.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "project_time", schema = "public")
public class ProjectTime implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "activitie_name")
    private String activitieName;

    @Column(name = "week_time_report")
    private int weekTimeReport;

    @Column(name = "week_date_report")
    private String weekDateReport;

    @Column(name = "percentage_report")
    private int percentageReport;

    @Column(name = "reported_at")
    private String reportedAt;
}
