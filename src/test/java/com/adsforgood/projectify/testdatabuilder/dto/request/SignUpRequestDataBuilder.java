package com.adsforgood.projectify.testdatabuilder.dto.request;

import com.adsforgood.projectify.domain.Role;
import com.adsforgood.projectify.dto.request.SignUpRequest;

public class SignUpRequestDataBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    public SignUpRequestDataBuilder(){
        firstName = "Pablo";
        lastName = "Tejada";
        email = "pablo@projectify.com";
        password = "1212345";
        role = Role.ADMIN;
    }

    public SignUpRequestDataBuilder userWithfirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public SignUpRequestDataBuilder userWithlastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public SignUpRequestDataBuilder userWithEmail(String email){
        this.email = email;
        return this;
    }

    public SignUpRequestDataBuilder userWithPassword(String password){
        this.password = password;
        return this;
    }

    public SignUpRequestDataBuilder userWithRole(Role role){
        this.role = role;
        return this;
    }

    public SignUpRequest build() {return new SignUpRequest(firstName, lastName, email, password, role);}
}
