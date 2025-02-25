package com.sparta.yuni.user.domain;

import lombok.Getter;

@Getter
public class UserInfo {

    private final String name;
    private final String profileImageUrl;
    private final String password;


    public UserInfo(String name, String profileImageUrl, String password) {

        if(name == null || name.isEmpty()) {
         throw new IllegalArgumentException();
        }
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.password = password;
    }

}
