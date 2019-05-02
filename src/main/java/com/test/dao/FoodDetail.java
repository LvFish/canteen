package com.test.dao;

import com.test.entity.FoodEntity;

import java.math.BigDecimal;

/**
 * Created by liujiawang on 2019/4/29.
 */
public class FoodDetail{
    private int foodId;
    private String foodName;
    private BigDecimal foodPrice;
    private int typeId;
    private int foodNumber;
    private int canteenId;
    private String foodUrl;
    private String foodDetail;
    private String typeName;
    private String canteenName;

    public FoodDetail(){

    }

    public FoodDetail(FoodEntity food){
        this.foodId = food.getFoodId();
        this.foodName = food.getFoodName();
        this.foodPrice = food.getFoodPrice();
        this.typeId = food.getTypeId();
        this.foodNumber = food.getFoodNumber();
        this.canteenId = food.getCanteenId();
        this.foodUrl = food.getFoodUrl();
        this.foodDetail = food.getFoodDetail();
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getFoodNumber() {
        return foodNumber;
    }

    public void setFoodNumber(int foodNumber) {
        this.foodNumber = foodNumber;
    }

    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public String getFoodUrl() {
        return foodUrl;
    }

    public void setFoodUrl(String foodUrl) {
        this.foodUrl = foodUrl;
    }

    public String getFoodDetail() {
        return foodDetail;
    }

    public void setFoodDetail(String foodDetail) {
        this.foodDetail = foodDetail;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCanteenName() {
        return canteenName;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }
}