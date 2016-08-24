package com.example.user.foodtracker;

/**
 * Created by user on 24/08/2016.
 */
public class CalorieCounter {

    public int mTotal;

    public void count(String calories){
        mTotal += Integer.parseInt(calories);
    }

    public String getTotal(){
        String result = Integer.toString(mTotal);
        return result;
    }
}
