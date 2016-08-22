package com.example.user.foodtracker;

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

    protected ArrayList<FoodItem> mSearchResults = new ArrayList<FoodItem>();

    public SetUpFoodResults(String query){
        mSearchResults = new ArrayList<FoodItem>();
        makeApiCall(query);
    }

    public void makeApiCall(String query){

        AsyncHttpClient client = new AsyncHttpClient();

        FatSecretSearch fatSecretSearch = new FatSecretSearch();
        String API_URL = fatSecretSearch.searchFood(query);
        Log.d("FatSecretUrl: ", API_URL);

        client.get(API_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(JSONObject jsonObject) {
                generateResults(jsonObject);
            }

            @Override
            public void onFailure(int statusCode, Throwable throwable, JSONObject error) {
                Log.e("FatSecretSearch:", "failure: " + statusCode + " " + throwable.getMessage());
            }
        });
    }

    private void generateResults(JSONObject apiResults){

        JSONObject object = apiResults.optJSONObject("foods");

        JSONArray jsonResults = object.optJSONArray("food");

        if (jsonResults != null) {
            for (int i = 0; i < jsonResults.length(); i++) {

                JSONObject jsonResult = jsonResults.optJSONObject(i);

                String foodName = jsonResult.optString("food_name");
                String foodDescription = jsonResult.optString("food_description");
                String foodBrand = jsonResult.optString("brand_name");
                String foodId = jsonResult.optString("food_id");
                String foodType = jsonResult.optString("food_type");
                String foodUrl = jsonResult.optString("food_url");

                FoodItem foodItem = new FoodItem(foodName, foodDescription, foodBrand, foodId, foodType, foodUrl);
                mSearchResults.add(foodItem);
            }
        }
    }

    public ArrayList<FoodItem> getSearchResults(){
        return mSearchResults;
    }
}

