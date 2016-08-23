package com.example.user.foodtracker;

import android.util.Log;

/**
 * Created by user on 23/08/2016.
 */
public class EdamamSearch {

    final static private String mAppKey = "&app_key=6c90c1546d69e0bdcecef30ff89de4df";
    final static private String mAppId = "b61eaecb";
    final static private String mApiUrl = "https://api.edamam.com/api/nutrition-data?app_id=";

    public String searchFood(String quantity, String measure, String food){
        return mApiUrl + mAppId + mAppKey + "&ingr=" + quantity + "%20" + measure + "%20" + food;
    }

}
