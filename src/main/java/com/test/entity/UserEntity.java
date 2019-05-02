package com.test.entity;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/4/29.
 */
@Entity
@Table(name = "user", schema = "canteen", catalog = "")
public class UserEntity {
    private int userId;
    private String userInfo;
    private String userOpenId;
    private String userNickName;
    private String userAvatarUrl;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "user_info", nullable = false, length = 255)
    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (userInfo != null ? !userInfo.equals(that.userInfo) : that.userInfo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (userInfo != null ? userInfo.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "user_open_id", nullable = false, length = 255)
    public String getUserOpenId() {
        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {
        this.userOpenId = userOpenId;
    }

    @Basic
    @Column(name = "user_nick_name", nullable = false, length = 255)
    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    @Basic
    @Column(name = "user_avatar_url", nullable = false, length = 255)
    public String getUserAvatarUrl() {
        return userAvatarUrl;
    }

    public void setUserAvatarUrl(String userAvatarUrl) {
        this.userAvatarUrl = userAvatarUrl;
    }
}
