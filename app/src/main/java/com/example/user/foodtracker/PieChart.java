package com.example.user.foodtracker;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by user on 24/08/2016.
 */
public class PieChart {

    public void generateChart(NutrientCounter nutrient, PieChartView chartView, TextView headerView, int rI, String title, TextView remainingHeaderView){
        List<SliceValue> values = new ArrayList<SliceValue>();

        SliceValue consumedSliceValue = new SliceValue(nutrient.getTotal(), Color.rgb(19,75,95));
        consumedSliceValue.setLabel(nutrient.getTotal().toString());
        values.add(consumedSliceValue);

        SliceValue remainingSliceValue = new SliceValue(nutrient.getRemainingRI(rI), Color.rgb(243,233,210));
        remainingSliceValue.setLabel(nutrient.getRemainingRI(rI).toString());
        values.add(remainingSliceValue);

        PieChartData pieChart = new PieChartData(values);

        chartView.setPieChartData(pieChart);
        chartView.setVisibility(View.VISIBLE);

        if(title != "Calories"){
            String headerText = title + ": " + nutrient.getTotal().toString() + "g";
            String remainingHeader = "RI: " + nutrient.getRemainingRI(rI).toString() + "g";
            headerView.setText(headerText);
            headerView.setVisibility(View.VISIBLE);

            remainingHeaderView.setText(remainingHeader);
            remainingHeaderView.setVisibility(View.VISIBLE);
        }else{
            String headerText = title + ": " + nutrient.getTotal().toString();
            String remainingHeader = "RI: " + nutrient.getRemainingRI(rI).toString();
            headerView.setText(headerText);
            headerView.setVisibility(View.VISIBLE);

            remainingHeaderView.setText(remainingHeader);
            remainingHeaderView.setVisibility(View.VISIBLE);
        }





    }

}
