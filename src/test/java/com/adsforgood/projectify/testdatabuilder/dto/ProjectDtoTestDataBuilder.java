package com.adsforgood.projectify.testdatabuilder.dto;

import com.adsforgood.projectify.dto.ProjectDto;

public class ProjectDtoTestDataBuilder {

    private Long id;

    private String name;

    private String description;

    public ProjectDtoTestDataBuilder(){
        name = "Instagram";
        description = "A social network where people can upload photos";
    }

    public ProjectDtoTestDataBuilder projectWithId(Long id){
        this.id = id;
        return this;
    }

    public ProjectDtoTestDataBuilder projectWithName(String name){
        this.name = name;
        return this;
    }

    public ProjectDtoTestDataBuilder projectWithDescripction(String description){
        this.description = description;
        return this;
    }

    public ProjectDto build() {return new ProjectDto(id, name, description);};
}
