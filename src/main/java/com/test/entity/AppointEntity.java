package com.test.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by liujiawang on 2019/5/2.
 */
@Entity
@Table(name = "appoint", schema = "canteen", catalog = "")
public class AppointEntity {
    private int apponitId;
    private int userId;
    private int foodId;
    private int canteenId;
    private Timestamp appointTime;

    @Id
    @Column(name = "apponit_id", nullable = false)
    public int getApponitId() {
        return apponitId;
    }

    public void setApponitId(int apponitId) {
        this.apponitId = apponitId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "food_id", nullable = false)
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Basic
    @Column(name = "canteen_id", nullable = false)
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Basic
    @Column(name = "appoint_time", nullable = false)
    public Timestamp getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(Timestamp appointTime) {
        this.appointTime = appointTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppointEntity that = (AppointEntity) o;

        if (apponitId != that.apponitId) return false;
        if (userId != that.userId) return false;
        if (foodId != that.foodId) return false;
        if (canteenId != that.canteenId) return false;
        if (appointTime != null ? !appointTime.equals(that.appointTime) : that.appointTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = apponitId;
        result = 31 * result + userId;
        result = 31 * result + foodId;
        result = 31 * result + canteenId;
        result = 31 * result + (appointTime != null ? appointTime.hashCode() : 0);
        return result;
    }
}
