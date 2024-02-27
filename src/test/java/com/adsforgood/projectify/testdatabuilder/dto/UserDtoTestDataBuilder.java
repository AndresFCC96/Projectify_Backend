package com.adsforgood.projectify.testdatabuilder.dto;


import com.adsforgood.projectify.domain.Role;
import com.adsforgood.projectify.dto.UserDto;


public class UserDtoTestDataBuilder {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private int weeklyPercentage;
    public UserDtoTestDataBuilder(){
        firstName = "Pablo";
        lastName = "Tejada";
        email = "pablo@projectify.com";
        password = "1212345";
        role = Role.ADMIN;
        weeklyPercentage = 100;
    }

    public UserDtoTestDataBuilder userWithId(Long id){
        this.id = id;
        return this;
    }

    public UserDtoTestDataBuilder userWithfirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserDtoTestDataBuilder userWithlastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserDtoTestDataBuilder userWithEmail(String email){
        this.email = email;
        return this;
    }

    public UserDtoTestDataBuilder userWithPassword(String password){
        this.password = password;
        return this;
    }
    public UserDtoTestDataBuilder userWithWeeklyPercentage(int weeklyPercentage){
        this.weeklyPercentage = weeklyPercentage;
        return this;
    }

    public UserDtoTestDataBuilder userWithRole(Role role){
        this.role = role;
        return this;
    }


    public UserDto build() {return new UserDto(id, firstName, lastName, email, password, weeklyPercentage, role);}
}
