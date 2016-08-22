package com.example.user.foodtracker;

/**
 * Created by user on 21/08/2016.
 */
public class FoodItem {
    private String mFoodName;
    private String mFoodDescription;
    private String mFoodBrand;
    private String mFoodId;
    private String mFoodType;
    private String mFoodUrl;

    public FoodItem(String foodName, String foodDescription, String foodBrand, String foodId, String foodType, String foodUrl) {
        super();
        mFoodName = foodName;
        mFoodDescription = foodDescription;
        mFoodBrand = foodBrand;
        mFoodId = foodId;
        mFoodType = foodType;
        mFoodUrl = foodUrl;
    }

    public String getID() {
        return mFoodId;
    }

    public String getBrand() {
        return mFoodBrand;
    }

    public String getTitle() {
        return mFoodName;
    }

    public String getFoodDescription() {
        return mFoodDescription;
//        Per 101g - Calories: 197kcal | Fat: 7.79g | Carbs: 0.00g | Protein: 29.80g
    }
}