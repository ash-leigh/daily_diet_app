package com.example.user.foodtracker;

import android.util.Log;
import android.widget.DatePicker;

/**
 * Created by user on 24/08/2016.
 */
public class DietAnalysisDatePickerFragment extends DatePickerFragment {

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = getDate(year, month, day);

        ActivityDietAnalysis originalActivity = (ActivityDietAnalysis) getActivity();
        originalActivity.dailyAnalysis(date);
    }

    @Override
    public String getDate(int year, int month, int day){
        Integer originalYear = year;
        Integer originalMonth = month;
        Integer originalDay = day;
        String date = originalYear.toString() + "-" + originalMonth.toString() + "-" + originalDay.toString();

        Log.d("date selected", date);
        return date;
    }
}
