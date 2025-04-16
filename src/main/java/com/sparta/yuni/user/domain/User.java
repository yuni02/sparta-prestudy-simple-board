package com.sparta.yuni.user.domain;

import com.sparta.yuni.common.domain.PositiveIntegerCounter;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private  Long id;


    private  UserInfo info;
    private  PositiveIntegerCounter followingCounter;

    private  PositiveIntegerCounter followerCounter;

    public User(Long id, UserInfo userInfo) {
        if(userInfo == null) {
            throw new IllegalArgumentException("userInfo cannot be null");
        }
        this.id = id;
        this.info = userInfo;
        this.followingCounter = new PositiveIntegerCounter();
        this.followerCounter = new PositiveIntegerCounter();
    }

    public void follow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException();
        }
        followingCounter.increase();
        targetUser.increaseFollowingCount();
    }

    public void unfollow(User targetUser){
        if(this.equals(targetUser)){
            throw new IllegalArgumentException();
        }
        followingCounter.decrease();
        targetUser.decreaseFollowerCount();
    }

    private void increaseFollowingCount(){
        followerCounter.increase();
    }

    private void decreaseFollowerCount(){
        followingCounter.decrease();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Long getId() {
        return id;
    }

    public UserInfo getInfo() {
        return info;
    }

    public int followingCounter() {
        return followingCounter.getCount();
    }

    public int followerCounter() {
        return followerCounter.getCount();
    }

    public String getName(){
        return info.getName();
    }

    public String getProfileImage(){
        return info.getProfileImageUrl();
    }



}
