package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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

    SetUpFoodResults mResults;
    FoodItem foodItem;

    Button mSaveButton;

    String mSelectedDate;
    String mSelectedMeal;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food_log);

        createDatabase();

        assignMeal();

        mSaveButton = (Button)findViewById(R.id.save_button);

//
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignFood();
            }
        });

//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                insertIntoDB();
//            }
//        });
    }

    protected void createDatabase(){
        db=openOrCreateDatabase("FOOD_DB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS FOOD_TRACKER(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, date VARCHAR, meal VARCHAR, food VARCHAR, foodCal VARCHAR, foodFat VARCHAR, foodSatFat VARCHAR, foodCarbs VARCHAR, foodSugar VARCHAR, foodProtien VARCHAR);");
    }

    protected void insertIntoDB(){

        String date = mSelectedDate;
        String meal = mSelectedMeal;

        EditText foodTextBox = (EditText)findViewById(R.id.search_text);
        String food = foodTextBox.getText().toString();


        FoodItem foodItem = mResults.getSearchResults();
        String foodCal = foodItem.getCalories();
        String foodFat = foodItem.getFat();
        String foodSatFat = foodItem.getSatFat();
        String foodCarb = foodItem.getCarbs();
        String foodSugar = foodItem.getSugar();
        String foodPro = foodItem.getProtien();


//        if(name.equals("") || add.equals("")){
//            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
//            return;
//        }
        String query = "INSERT INTO FOOD_TRACKER (date,meal,food,foodCal,foodFat,foodSatFat,foodCarbs,foodSugar,foodProtien) VALUES('"+date+"', '"+meal+"','"+food+"', '"+foodCal+"', '"+foodFat+"', '"+foodSatFat+"', '"+foodCarb+"', '"+foodSugar+"', '"+foodPro+"');";
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
        Log.d("API func", "STARTED");
        EditText foodSearch = (EditText)findViewById(R.id.search_text);
        EditText quantitySearch = (EditText)findViewById(R.id.quantity_text);
        EditText measureSearch = (EditText)findViewById(R.id.measure_text);

        String foodText = foodSearch.getText().toString();
        String quantityText = quantitySearch.getText().toString();
        String measureText = measureSearch.getText().toString();

        String query = foodText;
        String quantityTest = quantityText;
        String measureTest = measureText;
        mResults = new SetUpFoodResults(quantityTest, measureTest, query);
        mResults.setCallingActivity(ActivityAddFoodLog.this);
        mResults.makeApiCall();
    }

//    public void showFood(){
//        FoodItem test = mResults.getSearchResults();
//        Log.d("searchResults: ", test.getCarbs());
//    }

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
