package org.fastcampus.user.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fastcampus.user.domain.User;
import com.fastcampus.user.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {

    private final UserInfo userInfo = new UserInfo("test", "");
    private User user1;
    private User user2;

    @BeforeEach
    void init() {
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }

    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        //when
        boolean value = user1.equals(user2);
        //then
        assertFalse(value);

    }

    @Test
    void givenTwoSameIdUser_whenEqual_thenReturnTrue() {
        //given
        User sameUser = new User(1L, userInfo);

        //when
        boolean isSame = user1.equals(sameUser);

        //then
        assertTrue(isSame);

    }

    @Test
    void givenTwoUser_whenHashCode_thenReturnFalse() {
        //when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        //then
        assertNotEquals(hashCode1, hashCode2);

    }

    @Test
    void givenTwoSameIdUser_whenHashCode_thenEqual() {
        //given
        User sameUser = new User(1L, userInfo);

        // when
        int hashCode1 = user1.hashCode();
        int sameUserHashCode = sameUser.hashCode();

        assertEquals(hashCode1, sameUserHashCode);
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount() {
        //when
        user1.follow(user2);

        assertEquals(1, user1.followingCounter());
        assertEquals(0, user1.followerCounter());
        assertEquals(0, user2.followingCounter());
        assertEquals(1, user2.followerCounter());
    }

    @Test
    void givenTwoUserUser1FollowUser2_whenUnfollow_thenDecreaseUserCount() {
        //given
        user1.follow(user2);
        user1.unfollow(user2);

        assertEquals(0, user1.followingCounter());
        assertEquals(0, user1.followerCounter());
        assertEquals(0, user2.followingCounter());
        assertEquals(0, user2.followerCounter());
    }

    @Test
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount(){
        user1.unfollow(user2);

        assertEquals(0, user1.followingCounter());
        assertEquals(0, user1.followerCounter());
        assertEquals(0, user2.followerCounter());
        assertEquals(0, user2.followingCounter());
    }

}
