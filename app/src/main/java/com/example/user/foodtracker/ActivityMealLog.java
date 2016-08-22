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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

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
        db = openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
    }

    public void populateAllLogs(){
        String COUNT_SQL = "SELECT COUNT(*) FROM foods;";
        String SELECT_SQL = "SELECT * FROM foods;";

        countCursor = db.rawQuery(COUNT_SQL, null);
        countCursor.moveToFirst();
        int count = countCursor.getInt(0);
        countCursor.close();

        cursor = db.rawQuery(SELECT_SQL, null);


        for(int i=0; i < count; i++){
            cursor.moveToPosition(i);
            Log.d("Counter test", "TEST");

            TableRow row = new TableRow(this);
            TextView dateText = new TextView(this);
            TextView mealText = new TextView(this);
            TextView foodText = new TextView(this);

            dateText.setText(cursor.getString(1));
            row.addView(dateText);

            mealText.setText(cursor.getString(2));
            row.addView(mealText);

            foodText.setText(cursor.getString(3));
            row.addView(foodText);

            mMealLogTable.addView(row);

        }
    }

    public void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new LogDatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public void dbQuery(String query){
        Log.d("DB call", "yep");
        String SELECT_SQL = "SELECT * FROM foods WHERE date = " + query;

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
