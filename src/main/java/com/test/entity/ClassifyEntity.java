package com.test.entity;

import javax.persistence.*;

/**
 * Created by liujiawang on 2019/4/30.
 */
@Entity
@Table(name = "classify", schema = "canteen", catalog = "")
public class ClassifyEntity {
    private int classifyId;
    private int classifyType;
    private int foodId;

    @Id
    @Column(name = "classify_id", nullable = false)
    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    @Basic
    @Column(name = "classify_type", nullable = false)
    public int getClassifyType() {
        return classifyType;
    }

    public void setClassifyType(int classifyType) {
        this.classifyType = classifyType;
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

        ClassifyEntity that = (ClassifyEntity) o;

        if (classifyId != that.classifyId) return false;
        if (classifyType != that.classifyType) return false;
        if (foodId != that.foodId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = classifyId;
        result = 31 * result + classifyType;
        result = 31 * result + foodId;
        return result;
    }
}
