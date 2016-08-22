package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by user on 21/08/2016.
 */
public class ActivityAddFoodLog extends AppCompatActivity{

//    SetUpFoodResults mResults;

    Button mSearchButton;
    Button mSaveButton;
    String mSelectedDate;
    String mSelectedMeal;
    String mSelectedFood;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_log);

        createDatabase();

        assignMeal();

        mSearchButton = (Button)findViewById(R.id.search_button);
        mSaveButton = (Button)findViewById(R.id.save);
//
//        String query = "chicken";
//        mResults = new SetUpFoodResults(query);
//
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignFood();
//                ArrayList<FoodItem> test = mResults.getSearchResults();
//                Log.d("searchResults: ", test.toString());
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertIntoDB();
            }
        });
    }



    protected void createDatabase(){
        db=openOrCreateDatabase("FoodDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS foods(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, date VARCHAR, meal VARCHAR, food VARCHAR);");
    }

    protected void insertIntoDB(){
        String date = mSelectedDate;
        String meal = mSelectedMeal;
        String food = mSelectedFood;
//        if(name.equals("") || add.equals("")){
//            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
//            return;
//        }
        String query = "INSERT INTO foods (date,meal,food) VALUES('"+date+"', '"+meal+"','"+food+"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
    }

    public void showDatePickerDialog(View view) {
        DialogFragment datePickerFragment = new AddDatePickerFragment();
        datePickerFragment.show(getFragmentManager(), "datePicker");
    }

    public void assignDate(String dateString){
        mSelectedDate = dateString;
    }

    public void assignMeal(){
        Button breakfast = (Button)findViewById(R.id.selected_breakfast_button);
        breakfast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSelectedMeal = "Breakfast";
                Log.d("test", mSelectedMeal);
            }
        });

        Button lunch = (Button)findViewById(R.id.selected_lunch_button);
        lunch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSelectedMeal = "Lunch";
                Log.d("test", mSelectedMeal);
            }
        });

        Button dinner = (Button)findViewById(R.id.selected_dinner_button);
        dinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSelectedMeal = "Dinner";
                Log.d("test", mSelectedMeal);
            }
        });

        Button snack = (Button)findViewById(R.id.selected_snack_button);
        snack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mSelectedMeal = "Snack";
                Log.d("test", mSelectedMeal);
            }
        });
    }

    public void assignFood(){
        EditText foodSearch = (EditText)findViewById(R.id.search_text);
        String foodText = foodSearch.getText().toString();
        mSelectedFood = foodText;
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
                Intent homeIntent = new Intent(ActivityAddFoodLog.this, ActivityMain.class);
                startActivity(homeIntent);
                return true;
            case R.id.profile_item:
                Intent userIntent = new Intent(ActivityAddFoodLog.this, ActivityUserProfile.class);
                startActivity(userIntent);
                return true;
            case R.id.meal_log_item:
                Intent logIntent = new Intent(ActivityAddFoodLog.this, ActivityMealLog.class);
                startActivity(logIntent);
                return true;
            case R.id.analysis_item:
                Intent analysisIntent = new Intent(ActivityAddFoodLog.this, ActivityDietAnalysis.class);
                startActivity(analysisIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
