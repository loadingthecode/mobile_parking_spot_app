package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import com.github.mikephil.charting.components.AxisBase;


import java.util.ArrayList;

public class ParkingTrends extends AppCompatActivity {

    BarChart trendsChart;
    String [] dayList={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Parking Trends"); // set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // adds back arrow
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_trends);

        trendsChart = (BarChart) findViewById(R.id.trendsGraph);

        trendsChart.setDrawBarShadow(false);
        trendsChart.setDrawValueAboveBar(true);
        trendsChart.setMaxVisibleValueCount(50);
        trendsChart.setPinchZoom(false);
        trendsChart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(30f, 0));
        barEntries.add(new BarEntry(10f, 1));
        barEntries.add(new BarEntry(26f, 2));
        barEntries.add(new BarEntry(3f, 3));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Time of Day");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS); // from barchart video indian speaker

        ArrayList<String> theTimes = new ArrayList<String>();
        theTimes.add("Before 9AM");
        theTimes.add("9AM-12PM");
        theTimes.add("12PM-3PM");
        theTimes.add("3PM-5PM");

        BarData numberOfParkings = new BarData(theTimes, barDataSet);

        trendsChart.getXAxis().setSpaceBetweenLabels(2); // edit the space between labels

        trendsChart.setData(numberOfParkings);

        trendsChart.setTouchEnabled(true);
        trendsChart.setDragEnabled(true);
        trendsChart.setScaleEnabled(true);

        barDataSet.setBarSpacePercent(75f);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, dayList);
        MaterialBetterSpinner daySpinner = (MaterialBetterSpinner)findViewById(R.id.trendsDayChooser);
        daySpinner.setAdapter(arrayAdapter);
    }
}
