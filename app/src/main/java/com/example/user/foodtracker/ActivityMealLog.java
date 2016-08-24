package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 21/08/2016.
 */
public class ActivityMealLog extends AppCompatActivity {

    TextView mSQLTest;
    private SQLiteDatabase db;
    private Cursor cursor;
    private Cursor countCursor;

    TableLayout mMealLogTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_log);

        mMealLogTable = (TableLayout)findViewById(R.id.meal_log_table);

        openDatabase();
        populateAllLogs();

    }


    protected void openDatabase() {
        db = openOrCreateDatabase("FOOD_DB", Context.MODE_PRIVATE, null);
    }

    public void populateAllLogs(){
        String COUNT_SQL = "SELECT COUNT(*) FROM FOOD_TRACKER;";
        String SELECT_SQL = "SELECT * FROM FOOD_TRACKER ORDER BY date DESC;";

        countCursor = db.rawQuery(COUNT_SQL, null);
        countCursor.moveToFirst();
        int count = countCursor.getInt(0);
        countCursor.close();

        cursor = db.rawQuery(SELECT_SQL, null);


        for(int i=0; i < count; i++){
            cursor.moveToPosition(i);

            TableRow headerRow = new TableRow(this);
            TextView mealText = new TextView(this);

            mealText.setText(cursor.getString(2));
            mealText.setBackgroundColor(Color.rgb(243, 233, 210));
            mealText.setPadding(10, 10, 10, 10);
            mealText.setTextSize(16.0f);
            mealText.setTextColor(Color.rgb(17, 75, 95));
            headerRow.addView(mealText);

            TextView dateText = new TextView(this);
            String dateRaw = cursor.getString(1);
            String[] pieces = dateRaw.split("-");

            String day = pieces[2];
            String month = pieces[1];
            String year = pieces[0];

            String dateFormatted = day + "/" + month + "/" + year;

            dateText.setText(dateFormatted);
            dateText.setBackgroundColor(Color.rgb(243, 233, 210));
            dateText.setPadding(10, 10, 10, 10);
            dateText.setTextSize(16.0f);
            dateText.setTextColor(Color.rgb(17, 75, 95));
            headerRow.addView(dateText);


            mMealLogTable.addView(headerRow);

            TableRow foodRow = new TableRow(this);

            TextView foodText = new TextView(this);
            foodText.setText(cursor.getString(3));
            foodText.setBackgroundColor(Color.rgb(255, 255, 255));
            foodText.setPadding(10, 10, 10, 10);
            foodText.setTextSize(28.0f);
            foodText.setTextColor(Color.rgb(17, 75, 95));
            foodRow.addView(foodText);

            TextView foodTextSpace = new TextView(this);
            foodTextSpace.setBackgroundColor(Color.rgb(255, 255, 255));
            foodTextSpace.setPadding(10, 10, 10, 10);
            foodTextSpace.setTextSize(28.0f);
            foodRow.addView(foodTextSpace);

            mMealLogTable.addView(foodRow);

            TableRow calFatHeaderRow = new TableRow(this);
            TextView calHeader = new TextView(this);
            calHeader.setText("Calories");
            calHeader.setBackgroundColor(Color.rgb(243, 233, 210));
            calHeader.setPadding(10, 10, 10, 10);
            calHeader.setTextSize(16.0f);
            calHeader.setTextColor(Color.rgb(17, 75, 95));
            calFatHeaderRow.addView(calHeader);

            TextView fatHeader = new TextView(this);
            fatHeader.setText("Fat");
            fatHeader.setBackgroundColor(Color.rgb(243, 233, 210));
            fatHeader.setPadding(10, 10, 10, 10);
            fatHeader.setTextSize(16.0f);
            fatHeader.setTextColor(Color.rgb(17, 75, 95));
            calFatHeaderRow.addView(fatHeader);

            mMealLogTable.addView(calFatHeaderRow);

            TableRow calFatRow = new TableRow(this);
            TextView foodCal = new TextView(this);
            foodCal.setText(cursor.getString(4));
            foodCal.setBackgroundColor(Color.rgb(255, 255, 255));
            foodCal.setPadding(10, 10, 10, 10);
            foodCal.setTextSize(16.0f);
            foodCal.setTextColor(Color.rgb(17, 75, 95));
            calFatRow.addView(foodCal);

            TextView foodFat = new TextView(this);
            foodFat.setText(cursor.getString(5));
            foodFat.setBackgroundColor(Color.rgb(255, 255, 255));
            foodFat.setPadding(10, 10, 10, 10);
            foodFat.setTextSize(16.0f);
            foodFat.setTextColor(Color.rgb(17, 75, 95));
            calFatRow.addView(foodFat);

            mMealLogTable.addView(calFatRow);

            TableRow satCarbHeaderRow = new TableRow(this);
            TextView satHeader = new TextView(this);
            satHeader.setText("Saturated Fat");
            satHeader.setBackgroundColor(Color.rgb(243, 233, 210));
            satHeader.setPadding(10, 10, 10, 10);
            satHeader.setTextSize(16.0f);
            satHeader.setTextColor(Color.rgb(17, 75, 95));
            satCarbHeaderRow.addView(satHeader);

            TextView carbHeader = new TextView(this);
            carbHeader.setText("Carbohydrates");
            carbHeader.setBackgroundColor(Color.rgb(243, 233, 210));
            carbHeader.setPadding(10, 10, 10, 10);
            carbHeader.setTextSize(16.0f);
            carbHeader.setTextColor(Color.rgb(17, 75, 95));
            satCarbHeaderRow.addView(carbHeader);

            mMealLogTable.addView(satCarbHeaderRow);

            TableRow satCarbRow = new TableRow(this);

            TextView foodSatFat = new TextView(this);
            foodSatFat.setText(cursor.getString(6));
            foodSatFat.setBackgroundColor(Color.rgb(255, 255, 255));
            foodSatFat.setPadding(10, 10, 10, 10);
            foodSatFat.setTextSize(16.0f);
            foodSatFat.setTextColor(Color.rgb(17, 75, 95));
            satCarbRow.addView(foodSatFat);

            TextView foodCarbs = new TextView(this);
            foodCarbs.setText(cursor.getString(7));
            foodCarbs.setBackgroundColor(Color.rgb(255, 255, 255));
            foodCarbs.setPadding(10, 10, 10, 10);
            foodCarbs.setTextSize(16.0f);
            foodCarbs.setTextColor(Color.rgb(17, 75, 95));
            satCarbRow.addView(foodCarbs);

            mMealLogTable.addView(satCarbRow);

            TableRow sugProHeaderRow = new TableRow(this);
            TextView sugHeader = new TextView(this);
            sugHeader.setText("Sugar");
            sugHeader.setBackgroundColor(Color.rgb(243, 233, 210));
            sugHeader.setPadding(10, 10, 10, 10);
            sugHeader.setTextSize(16.0f);
            sugHeader.setTextColor(Color.rgb(17, 75, 95));
            sugProHeaderRow.addView(sugHeader);

            TextView proHeader = new TextView(this);
            proHeader.setText("Protein");
            proHeader.setBackgroundColor(Color.rgb(243, 233, 210));
            proHeader.setPadding(10, 10, 10, 10);
            proHeader.setTextSize(16.0f);
            proHeader.setTextColor(Color.rgb(17, 75, 95));
            sugProHeaderRow.addView(proHeader);

            mMealLogTable.addView(sugProHeaderRow);

            TableRow sugProRow = new TableRow(this);
            TextView foodSugar = new TextView(this);
            foodSugar.setText(cursor.getString(8));
            foodSugar.setBackgroundColor(Color.rgb(255, 255, 255));
            foodSugar.setPadding(12, 12, 12, 12);
            foodSugar.setTextSize(16.0f);
            foodSugar.setTextColor(Color.rgb(17, 75, 95));
            sugProRow.addView(foodSugar);

            TextView foodProtien = new TextView(this);
            foodProtien.setText(cursor.getString(9));
            foodProtien.setBackgroundColor(Color.rgb(255, 255, 255));
            foodProtien.setPadding(10, 10, 10, 10);
            foodProtien.setTextSize(16.0f);
            foodProtien.setTextColor(Color.rgb(17, 75, 95));
            sugProRow.addView(foodProtien);

            mMealLogTable.addView(sugProRow);

            TableRow spaceRow = new TableRow(this);
            TextView spaceCol = new TextView(this);
            spaceRow.addView(spaceCol);
            foodProtien.setPadding(12, 12, 12, 12);
            mMealLogTable.addView(spaceRow);
        }
    }

    public void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new LogDatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public void dbQuery(String query){
        Log.d("DB call", "yep");
        String SELECT_SQL = "SELECT * FROM FOOD_TRACKER WHERE date = " + query;

        cursor = db.rawQuery(SELECT_SQL, null);
        Log.d("DB Check", cursor.toString());

        cursor.moveToFirst();
        showRecords();
    }

    public void showRecords() {
        String id = cursor.getString(0);
        String date = cursor.getString(1);
        String meal = cursor.getString(2);
        String food = cursor.getString(3);
        mSQLTest.setText(id + date + meal + food);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.bottom_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home_item:
                Intent homeIntent = new Intent(ActivityMealLog.this, ActivityMain.class);
                startActivity(homeIntent);
                return true;
            case R.id.profile_item:
                Intent userIntent = new Intent(ActivityMealLog.this, ActivityUserProfile.class);
                startActivity(userIntent);
                return true;
            case R.id.meal_log_item:
                Intent logIntent = new Intent(ActivityMealLog.this, ActivityMealLog.class);
                startActivity(logIntent);
                return true;
            case R.id.analysis_item:
                Intent analysisIntent = new Intent(ActivityMealLog.this, ActivityDietAnalysis.class);
                startActivity(analysisIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addNewLog(View view){
        Intent addNewIntent = new Intent(ActivityMealLog.this, ActivityAddFoodLog.class);
        startActivity(addNewIntent);
    }



}
