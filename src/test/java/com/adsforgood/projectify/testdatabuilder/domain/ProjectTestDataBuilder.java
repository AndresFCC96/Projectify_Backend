package com.adsforgood.projectify.testdatabuilder.domain;

import com.adsforgood.projectify.domain.Project;

public class ProjectTestDataBuilder {

    private Long id;

    private String name;

    private String description;

    public ProjectTestDataBuilder(){
        name = "Instagram";
        description = "A social network where people can upload photos";
    }

    public ProjectTestDataBuilder projectWithId(Long id){
        this.id = id;
        return this;
    }

    public ProjectTestDataBuilder projectWithName(String name){
        this.name = name;
        return this;
    }

    public ProjectTestDataBuilder projectWithDescripction(String description){
        this.description = description;
        return this;
    }

    public Project build() {return new Project(id, name, description);};
}
