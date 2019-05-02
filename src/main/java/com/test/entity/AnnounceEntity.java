package com.test.entity;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by liujiawang on 2019/4/29.
 */
@Entity
@Table(name = "announce", schema = "canteen", catalog = "")
public class AnnounceEntity {
    private int announceId;
    private String announceDetail;
    private Date announceTime;
    private int foodId;

    @Id
    @Column(name = "announce_id", nullable = false)
    public int getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(int announceId) {
        this.announceId = announceId;
    }

    @Basic
    @Column(name = "announce_detail", nullable = false, length = 255)
    public String getAnnounceDetail() {
        return announceDetail;
    }

    public void setAnnounceDetail(String announceDetail) {
        this.announceDetail = announceDetail;
    }

    @Basic
    @Column(name = "announce_time", nullable = false)
    public Date getAnnounceTime() {
        return announceTime;
    }

    public void setAnnounceTime(Date announceTime) {
        this.announceTime = announceTime;
    }

    @Basic
    @Column(name = "food_id", nullable = false)
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnnounceEntity that = (AnnounceEntity) o;

        if (announceId != that.announceId) return false;
        if (foodId != that.foodId) return false;
        if (announceDetail != null ? !announceDetail.equals(that.announceDetail) : that.announceDetail != null)
            return false;
        if (announceTime != null ? !announceTime.equals(that.announceTime) : that.announceTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = announceId;
        result = 31 * result + (announceDetail != null ? announceDetail.hashCode() : 0);
        result = 31 * result + (announceTime != null ? announceTime.hashCode() : 0);
        result = 31 * result + foodId;
        return result;
    }
}
