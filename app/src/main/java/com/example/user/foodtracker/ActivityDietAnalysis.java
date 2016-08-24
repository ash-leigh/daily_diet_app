package com.example.user.foodtracker;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

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

        PieChartView calChartView = (PieChartView)findViewById(R.id.cal_chart);
        TextView calHeaderView = (TextView)findViewById(R.id.calories_analysis_table_header);
        TextView calRemainingView = (TextView)findViewById(R.id.calories_remaining_table_header);
        PieChart calChart = new PieChart();
        calChart.generateChart(calCount, calChartView, calHeaderView, 2000, "Calories", calRemainingView);

        PieChartView fatChartView = (PieChartView)findViewById(R.id.fat_chart);
        TextView fatHeaderView = (TextView)findViewById(R.id.fat_analysis_table_header);
        TextView fatRemainingView = (TextView)findViewById(R.id.fat_remaining_table_header);
        PieChart fatChart = new PieChart();
        fatChart.generateChart(fatCount, fatChartView, fatHeaderView, 70, "Fat", fatRemainingView);

        PieChartView satFatChartView = (PieChartView)findViewById(R.id.satFat_chart);
        TextView satFatHeaderView = (TextView)findViewById(R.id.satFat_analysis_table_header);
        TextView satFatRemainingView = (TextView)findViewById(R.id.satFat_remaining_table_header);
        PieChart satFatChart = new PieChart();
        satFatChart.generateChart(satFatCount, satFatChartView, satFatHeaderView, 20, "Saturated Fat", satFatRemainingView);

        PieChartView carbChartView = (PieChartView)findViewById(R.id.carb_chart);
        TextView carbHeaderView = (TextView)findViewById(R.id.carbs_analysis_table_header);
        TextView carbsRemainingView = (TextView)findViewById(R.id.carbs_remaining_table_header);
        PieChart carbChart = new PieChart();
        carbChart.generateChart(carbCount, carbChartView, carbHeaderView, 260, "Carbohydrates", carbsRemainingView);

        PieChartView sugarChartView = (PieChartView)findViewById(R.id.sugar_chart);
        TextView sugHeaderView = (TextView)findViewById(R.id.sugar_analysis_table_header);
        TextView sugarRemainingView = (TextView)findViewById(R.id.sugar_remaining_table_header);
        PieChart sugarChart = new PieChart();
        sugarChart.generateChart(sugarCount, sugarChartView, sugHeaderView, 90, "Sugar", sugarRemainingView);

        PieChartView protienChartView = (PieChartView)findViewById(R.id.pro_chart);
        TextView proHeaderView = (TextView)findViewById(R.id.pro_analysis_table_header);
        TextView proRemainingView = (TextView)findViewById(R.id.pro_remaining_table_header);
        PieChart proChart = new PieChart();
        proChart.generateChart(protienCount, protienChartView, proHeaderView, 50, "Protein", proRemainingView);

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
