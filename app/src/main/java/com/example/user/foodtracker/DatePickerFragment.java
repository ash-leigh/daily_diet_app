package com.example.user.foodtracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by user on 21/08/2016.
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        Log.d("TEST", getActivity().toString());
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        String date = getDate(year, month, day);
    }

    public String getDate(int year, int month, int day){
        Integer originalYear = year;
        Integer originalMonth = month;
        Integer originalDay = day;
        String date = originalYear.toString() + "-" + originalMonth.toString() + "-" + originalDay.toString();
        Log.d("date selected", date);
        return date;
    }
}

