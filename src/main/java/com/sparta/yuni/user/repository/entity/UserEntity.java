package com.sparta.yuni.user.repository.entity;

import com.sparta.yuni.common.domain.PositiveIntegerCounter;
import com.sparta.yuni.common.domain.repository.entity.TimeBaseEntity;
import com.sparta.yuni.user.domain.User;
import com.sparta.yuni.user.domain.UserInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sample_board_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    private String profileImage;
    private Integer followerCount;
    private Integer followingCount;


    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.followerCounter();
        this.followingCount = user.followingCounter();

    }

    public User toUser(){
        return User.builder()
            .id(id)
            .info(new UserInfo(name, profileImage, password))
            .followerCounter(new PositiveIntegerCounter(followerCount))
            .followingCounter(new PositiveIntegerCounter(followingCount))
            .build();
    }



}
