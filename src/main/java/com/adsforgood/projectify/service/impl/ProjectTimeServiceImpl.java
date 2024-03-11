package com.adsforgood.projectify.service.impl;

import com.adsforgood.projectify.Exception.ExceptionManager;
import com.adsforgood.projectify.domain.ProjectTime;
import com.adsforgood.projectify.domain.User;
import com.adsforgood.projectify.dto.ProjectTimeDto;
import com.adsforgood.projectify.dto.UserDto;
import com.adsforgood.projectify.mapper.ProjectTimeMapper;
import com.adsforgood.projectify.mapper.UserMapper;
import com.adsforgood.projectify.repository.ProjectTimeRepository;
import com.adsforgood.projectify.service.ProjectService;
import com.adsforgood.projectify.service.ProjectTimeService;
import com.adsforgood.projectify.service.UserService;
import com.adsforgood.projectify.utility.Utils;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ProjectTimeServiceImpl implements ProjectTimeService {

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectTimeRepository projectTimeRepository;

    @Override
    public int dateToISO8601(String date) throws Exception {
        if (!Utils.isAString(date) || !Utils.isAValidStringDate(date)){
            throw new ExceptionManager.InvalidDateException();
        }else{
            validateDate(date);
            Calendar cal = Utils.stringDateToDate(date);
            return Utils.convertDateToISO8601(cal);
        }
    }

    @Override
    public ProjectTime reportTime(ProjectTimeDto projectTimeDto) throws Exception {
        if (!Utils.isAnObject(projectTimeDto)){
            throw new ExceptionManager.NotAValidEntity("ProjectTimeDto");
        }else{
            validateId("ProjectId", projectTimeDto.getProjectId());
            validateId("UserId", projectTimeDto.getUserId());
            validateActivitieName(projectTimeDto.getActivitieName());
            validateTimReportOrPercentageReport("WeekTimeReport", projectTimeDto.getWeekTimeReport());
            validateTimReportOrPercentageReport("PercentageReport", projectTimeDto.getPercentageReport());
            validateDate(projectTimeDto.getReportedAt().toString());
            projectService.findProjectById(projectTimeDto.getProjectId().toString());
            User user = userService.findById(projectTimeDto.getUserId().toString());
            if (user.getWeeklyPercentage() > projectTimeDto.getPercentageReport()) {
                user.setWeeklyPercentage(user.getWeeklyPercentage() - projectTimeDto.getPercentageReport());
            } else {
                user.setWeeklyPercentage(projectTimeDto.getPercentageReport() - user.getWeeklyPercentage());
            }
            UserDto userDto = UserMapper.convertUserToUserDto(user);
            userDto.setId(user.getId());
            userService.modifyUser(userDto);
            ProjectTime projectTime = ProjectTimeMapper.convertProjectTimeDtotoProjectTime(projectTimeDto);
            return projectTimeRepository.save(projectTime);
        }
    }

    @Override
    public List<ProjectTime> getAllReportsPerUserPerProject(Long idProject, Long userId) {
        if (!Utils.isNumeric(idProject.toString())){
            throw new ExceptionManager.InvalidFieldException("IdProject", idProject.toString());
        }
        if (!Utils.isNumeric(userId.toString())){
            throw new ExceptionManager.InvalidFieldException("userId", userId.toString());
        }
        List<ProjectTime> historicalReportsPerUserPerProject = projectTimeRepository.findByProjectIdAndUserId(idProject, userId);
        if(historicalReportsPerUserPerProject.isEmpty()){
            throw new ExceptionManager.EmptyListException("User Reports Per Project");
        }else{
            return historicalReportsPerUserPerProject;
        }
    }

    @Override
    public boolean validateDate(String date) throws Exception {
        if (!Utils.isAValidStringDate(date)){
            throw new ExceptionManager.InvalidDateException();
        }
        return true;
    }

    @Override
    public boolean validateId(String field, Long id) throws Exception {
        if (!Utils.isNumeric(id.toString())){
            throw new ExceptionManager.InvalidFieldException(field, id.toString());
        }
        return false;
    }

    @Override
    public boolean validateActivitieName(String name) throws Exception {
        if(!Utils.isAString(name)){
            throw new ExceptionManager.EmptyFieldException("Name");
        }
        return false;
    }

    @Override
    public boolean validateTimReportOrPercentageReport(String field, int report) throws Exception {
        if (!Utils.isNumeric(String.valueOf(report))){
            throw new ExceptionManager.InvalidFieldException(field, String.valueOf(report));
        }
        return false;
    }
}
