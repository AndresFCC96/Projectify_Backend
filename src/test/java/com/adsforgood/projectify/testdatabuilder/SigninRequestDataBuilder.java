package com.adsforgood.projectify.testdatabuilder;

import com.adsforgood.projectify.dao.request.SignInRequest;

public class SigninRequestDataBuilder {

    private String email;
    private String password;

    public SigninRequestDataBuilder() {
        email = email;
        password = password;
    }

    public SigninRequestDataBuilder userWithEmail(String email){
        this.email = email;
        return this;
    }

    public SigninRequestDataBuilder userWithPassword(String password){
        this.password = password;
        return this;
    }

    public SignInRequest build() {return new SignInRequest(email, password);}

}
