package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

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
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(30f, 0));
        barEntries.add(new BarEntry(10f, 1));
        barEntries.add(new BarEntry(26f, 2));
        barEntries.add(new BarEntry(3f, 3));
        BarDataSet barDataSet = new BarDataSet(barEntries, "Time of Day");

        ArrayList<String> theTimes = new ArrayList<String>();
        theTimes.add("Early Morning");
        theTimes.add("Late Morning");
        theTimes.add("Early Afternoon");
        theTimes.add("Late Afternoon");

        BarData numberOfParkings = new BarData(theTimes, barDataSet);
        trendsChart.setData(numberOfParkings);

        trendsChart.setTouchEnabled(true);
        trendsChart.setDragEnabled(true);
        trendsChart.setScaleEnabled(true);

        trendsChart.setMaxVisibleValueCount(4);

        // TODO: read documentation to make bars thinner

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, dayList);
        MaterialBetterSpinner daySpinner = (MaterialBetterSpinner)findViewById(R.id.trendsDayChooser);
        daySpinner.setAdapter(arrayAdapter);
    }
}
