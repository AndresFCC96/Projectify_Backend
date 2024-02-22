package com.adsforgood.projectify.testdatabuilder;

import com.adsforgood.projectify.domain.User;

public class UserTestDataBuilder {

    private Long id;
    private String email;
    private String password;

    public UserTestDataBuilder(){
        email = "pablo@projectify.com";
        password = "1212345";
    }

    public UserTestDataBuilder userWithId(Long id){
        this.id = id;
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

    public User build() {
        return new User(id, email, password);
    }
}
