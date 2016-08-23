package com.example.user.foodtracker;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by user on 21/08/2016.
 */
public class SetUpFoodResults {

    protected FoodItem mSearchResults;
    protected boolean mApiFinished;
    protected Activity mCaller;

    String mQuantity;
    String mMeasure;
    String mFood;

    public SetUpFoodResults(String quantity, String measure, String food){
        mQuantity = quantity;
        mMeasure = measure;
        mFood = food;
    }


    public void setCallingActivity(Activity callingActivity){
        mCaller = callingActivity;
    }

    public void makeApiCall(){
        String quantity = mQuantity;
        String food = mFood;
        String measure = mMeasure;

        AsyncHttpClient client = new AsyncHttpClient();

        EdamamSearch fatSecretSearch = new EdamamSearch();
        String API_URL = fatSecretSearch.searchFood(quantity, measure, food);

        client.get(API_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                generateResults(jsonObject);

                ActivityAddFoodLog callingActivity = (ActivityAddFoodLog) mCaller;
                callingActivity.insertIntoDB();

            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Log.e("FatSecretSearch:", "failure: " + statusCode + " " + throwable.getMessage());
            }
        });
    }

    private void generateResults(JSONObject apiResults){
        JSONObject object = apiResults.optJSONObject("totalNutrients");

        JSONObject calObject = object.optJSONObject("ENERC_KCAL");
        String foodCalories = calObject.optString("quantity");

        JSONObject satObject = object.optJSONObject("FASAT");
        String foodSaturatedFat = satObject.optString("quantity");
        foodSaturatedFat = foodSaturatedFat.substring(0,4);

        JSONObject fatObject = object.optJSONObject("FAT");
        String foodFat = fatObject.optString("quantity");
        foodFat = foodFat.substring(0,4);

        JSONObject carbObject = object.optJSONObject("CHOCDF");
        String foodCarbs = carbObject.optString("quantity");
        foodCarbs = foodCarbs.substring(0,4);

        JSONObject sugObject = object.optJSONObject("SUGAR");
        String foodSugar = sugObject.optString("quantity");
        foodSugar = foodSugar.substring(0,4);

        JSONObject proObject = object.optJSONObject("PROCNT");
        String foodProtien = proObject.optString("quantity");
        foodProtien = foodProtien.substring(0,4);

        FoodItem foodItem = new FoodItem(foodCalories, foodFat, foodSaturatedFat, foodCarbs, foodSugar, foodProtien);
        mSearchResults = foodItem;
    }

    public FoodItem getSearchResults(){
        Log.d("TEST", "get being called");
        return mSearchResults;
    }
}

