package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by user on 21/08/2016.
 */
public class ActivityDietAnalysis extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    private Cursor countCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_analysis);
        openDatabase();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_menu, menu);
        return true;
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("FOOD_DB", Context.MODE_PRIVATE, null);
    }

    public void dailyAnalysis(String date){
        Log.d("DATE check", date);

        NutrientCounter calCount = new NutrientCounter();
        NutrientCounter fatCount = new NutrientCounter();
        NutrientCounter satFatCount = new NutrientCounter();
        NutrientCounter carbCount = new NutrientCounter();
        NutrientCounter sugarCount = new NutrientCounter();
        NutrientCounter protienCount = new NutrientCounter();

        Log.d("DB call", "yep");

        String COUNT_SQL = "SELECT COUNT(*) FROM FOOD_TRACKER WHERE date=" + "'"+ date + "'" + ";";
        String SELECT_SQL = "SELECT * FROM FOOD_TRACKER WHERE date =" + "'" + date + "'" + ";";

        countCursor = db.rawQuery(COUNT_SQL, null);
        countCursor.moveToFirst();
        int count = countCursor.getInt(0);
        countCursor.close();

        cursor = db.rawQuery(SELECT_SQL, null);

        for(int i=0; i < count; i++) {
            cursor.moveToPosition(i);

            String calories = cursor.getString(4);
            calCount.count(calories);

            String fat = cursor.getString(5);
            fatCount.count(fat);

            String satFat = cursor.getString(6);
            satFatCount.count(satFat);

            String carbs = cursor.getString(7);
            carbCount.count(carbs);

            String sugar = cursor.getString(8);
            sugarCount.count(sugar);

            String protien = cursor.getString(9);
            protienCount.count(protien);
        }

        String calCheck = calCount.getTotal();
        Log.d("CALCHECK", calCheck);

        String fatCheck = fatCount.getTotal();
        Log.d("FATCHECK", fatCheck);

        String satFatCheck = satFatCount.getTotal();
        Log.d("SATFATCHECK", satFatCheck);

        String carbCheck = carbCount.getTotal();
        Log.d("CARBCHECK", carbCheck);

        String sugarCheck = sugarCount.getTotal();
        Log.d("SUGARCHECK", sugarCheck);

        String protienCheck = protienCount.getTotal();
        Log.d("PROCHECK", protienCheck);
    }

    public void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new DietAnalysisDatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home_item:
                Intent homeIntent = new Intent(ActivityDietAnalysis.this, ActivityMain.class);
                startActivity(homeIntent);
                return true;
            case R.id.profile_item:
                Intent userIntent = new Intent(ActivityDietAnalysis.this, ActivityUserProfile.class);
                startActivity(userIntent);
                return true;
            case R.id.meal_log_item:
                Intent logIntent = new Intent(ActivityDietAnalysis.this, ActivityMealLog.class);
                startActivity(logIntent);
                return true;
            case R.id.analysis_item:
                Intent analysisIntent = new Intent(ActivityDietAnalysis.this, ActivityDietAnalysis.class);
                startActivity(analysisIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
