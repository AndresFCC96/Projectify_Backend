package com.adsforgood.projectify.repository;

import com.adsforgood.projectify.domain.ProjectTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTimeRepository extends JpaRepository<ProjectTime, Long> {

    List<ProjectTime> findByProjectIdAndUserId(Long projectId, Long userId);

}
