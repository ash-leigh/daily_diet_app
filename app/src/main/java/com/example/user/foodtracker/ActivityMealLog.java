package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import org.w3c.dom.Text;

/**
 * Created by user on 21/08/2016.
 */
public class ActivityMealLog extends AppCompatActivity {

//    private CoordinatorLayout coordinatorLayout;

    TextView mSQLTest;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_log);

        openDatabase();
        Log.d("DB Check", db.toString());

        mSQLTest = (TextView)findViewById(R.id.SQLTest);


//        mSQLTest = (TextView)findViewById(R.id.SQLTest);
//        mSQLTest.setText();

//        BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
//        bottomBar.setItemsFromMenu(R.menu.bottom_menu, new OnMenuTabSelectedListener() {
//
//            @Override
//            public void onMenuItemSelected(int itemId) {
//                switch (itemId) {
//                    case R.id.home_item:
//                        Log.d("TEST: ", "HOME");
//                        Intent homeIntent = new Intent(ActivityMealLog.this, ActivityMain.class);
//                        startActivity(homeIntent);
//                        break;
//                    case R.id.profile_item:
//                        Log.d("TEST: ", "USER");
//                        Intent profileIntent = new Intent(ActivityMealLog.this, ActivityUserProfile.class);
//                        startActivity(profileIntent);
//                        break;
//                    case R.id.meal_log_item:
//                        Log.d("TEST: ", "LOG");
//                        Intent logIntent = new Intent(ActivityMealLog.this, ActivityMealLog.class);
//                        startActivity(logIntent);
//                        break;
//                    case R.id.analysis_item:
//                        Log.d("TEST: ", "ANALYSIS");
//                        Intent analysisIntent = new Intent(ActivityMealLog.this, ActivityDietAnalysis.class);
//                        startActivity(analysisIntent);
//                        break;
//                }
//            }
//        });
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
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
