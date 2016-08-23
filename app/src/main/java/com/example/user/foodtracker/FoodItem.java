package com.example.user.foodtracker;

/**
 * Created by user on 21/08/2016.
 */
public class FoodItem {
    private String mFoodCalories;
    private String mFoodFat;
    private String mFoodSauratedFat;
    private String mFoodCarbs;
    private String mFoodSugar;
    private String mFoodProtien;

    public FoodItem(String foodCalories, String foodFat, String foodSaturatedFat, String foodCarbs, String foodSugar, String foodProtien) {
        mFoodCalories = foodCalories;
        mFoodFat = foodFat;
        mFoodSauratedFat = foodSaturatedFat;
        mFoodCarbs = foodCarbs;
        mFoodSugar = foodSugar;
        mFoodProtien = foodProtien;
    }

    public String getCalories() {
        return mFoodCalories;
    }

    public String getFat() {
        return mFoodFat;
    }

    public String getSatFat() {
        return mFoodSauratedFat;
    }

    public String getCarbs(){
        return mFoodCarbs;
    }

    public String getSugar(){
        return mFoodSugar;
    }

    public String getProtien(){
        return mFoodProtien;
    }
}