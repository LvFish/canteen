package com.test.entity;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/4/29.
 */
@Entity
@Table(name = "canteens", schema = "canteen", catalog = "")
public class CanteensEntity {
    private int canteenId;
    private String canteenName;

    @Id
    @Column(name = "canteen_id", nullable = false)
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Basic
    @Column(name = "canteen_name", nullable = false, length = 255)
    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CanteensEntity that = (CanteensEntity) o;

        if (canteenId != that.canteenId) return false;
        if (canteenName != null ? !canteenName.equals(that.canteenName) : that.canteenName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = canteenId;
        result = 31 * result + (canteenName != null ? canteenName.hashCode() : 0);
        return result;
    }
}
