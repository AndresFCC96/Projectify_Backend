package com.adsforgood.projectify.testdatabuilder.domain;


import com.adsforgood.projectify.domain.Role;
import com.adsforgood.projectify.domain.User;


public class UserTestDataBuilder {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private int weeklyPercentage;
    public UserTestDataBuilder(){
        firstName = "Pablo";
        lastName = "Tejada";
        email = "pablo@projectify.com";
        password = "1212345";
        role = Role.ADMIN;
        weeklyPercentage = 100;
    }

    public UserTestDataBuilder userWithId(Long id){
        this.id = id;
        return this;
    }

    public UserTestDataBuilder userWithfirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserTestDataBuilder userWithlastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserTestDataBuilder userWithEmail(String email){
        this.email = email;
        return this;
    }

    public UserTestDataBuilder userWithPassword(String password){
        this.password = password;
        return this;
    }
    public UserTestDataBuilder userWithWeeklyPercentage(int weeklyPercentage){
        this.weeklyPercentage = weeklyPercentage;
        return this;
    }

    public UserTestDataBuilder userWithRole(Role role){
        this.role = role;
        return this;
    }


    public User build() {return new User(id, firstName, lastName, email, password, weeklyPercentage, role);}
}
