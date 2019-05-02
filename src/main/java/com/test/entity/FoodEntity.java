package com.test.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by liujiawang on 2019/4/29.
 */
@Entity
@Table(name = "food", schema = "canteen", catalog = "")
public class FoodEntity {
    private int foodId;
    private String foodName;
    private BigDecimal foodPrice;
    private int typeId;
    private int foodNumber;
    private int canteenId;
    private String foodUrl;
    private String foodDetail;

    @Id
    @Column(name = "food_id", nullable = false)
    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    @Basic
    @Column(name = "food_name", nullable = false, length = 255)
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Basic
    @Column(name = "food_price", nullable = false, precision = 2)
    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    @Basic
    @Column(name = "type_id", nullable = false)
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    @Basic
    @Column(name = "food_number", nullable = false)
    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
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
    @Column(name = "food_url", nullable = false, length = 255)
    public String getFoodUrl() {
        return foodUrl;
    }

    public void setFoodUrl(String foodUrl) {
        this.foodUrl = foodUrl;
    }

    @Basic
    @Column(name = "food_detail", nullable = false, length = 255)
    public String getFoodDetail() {
        return foodDetail;
    }

    public void setFoodDetail(String foodDetail) {
        this.foodDetail = foodDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodEntity that = (FoodEntity) o;

        if (foodId != that.foodId) return false;
        if (typeId != that.typeId) return false;
        if (foodNumber != that.foodNumber) return false;
        if (canteenId != that.canteenId) return false;
        if (foodName != null ? !foodName.equals(that.foodName) : that.foodName != null) return false;
        if (foodPrice != null ? !foodPrice.equals(that.foodPrice) : that.foodPrice != null) return false;
        if (foodUrl != null ? !foodUrl.equals(that.foodUrl) : that.foodUrl != null) return false;
        if (foodDetail != null ? !foodDetail.equals(that.foodDetail) : that.foodDetail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = foodId;
        result = 31 * result + (foodName != null ? foodName.hashCode() : 0);
        result = 31 * result + (foodPrice != null ? foodPrice.hashCode() : 0);
        result = 31 * result + typeId;
        result = 31 * result + foodNumber;
        result = 31 * result + canteenId;
        result = 31 * result + (foodUrl != null ? foodUrl.hashCode() : 0);
        result = 31 * result + (foodDetail != null ? foodDetail.hashCode() : 0);
        return result;
    }
}
