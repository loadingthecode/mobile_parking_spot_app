package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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

public class ParkingTrends extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    BarChart trendsChart;
    String [] dayList={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    BarDataSet barDataSet = new BarDataSet(barEntries, "Time of Day");

    BarEntry earlyMorning;
    BarEntry lateMorning;
    BarEntry earlyAfternoon;
    BarEntry lateAfternoon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_trends);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("Parking Trends"); // set actionbar title
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        Spinner spinner = findViewById(R.id.trendsDayChooser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dayOfWeek, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);


        trendsChart = (BarChart) findViewById(R.id.trendsGraph);

        trendsChart.setDrawBarShadow(false);
        trendsChart.setDrawValueAboveBar(true);
        trendsChart.setMaxVisibleValueCount(50);
        //trendsChart.setPinchZoom(false);
        trendsChart.setDrawGridBackground(true);

        earlyMorning = new BarEntry(50f, 0);
        lateMorning = new BarEntry(10f, 1);
        earlyAfternoon = new BarEntry(26f, 2);
        lateAfternoon = new BarEntry(3f, 3);

        barEntries.add(earlyMorning);
        barEntries.add(lateMorning);
        barEntries.add(earlyAfternoon);
        barEntries.add(lateAfternoon);

        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS); // from barchart video indian speaker

        ArrayList<String> theTimes = new ArrayList<String>();
        theTimes.add("Before 9AM");
        theTimes.add("9AM-12PM");
        theTimes.add("12PM-3PM");
        theTimes.add("3PM-5PM");

        BarData numberOfParkings = new BarData(theTimes, barDataSet);

        trendsChart.getXAxis().setSpaceBetweenLabels(2); // edit the space between labels

        TextView yAxisLabel = (TextView)findViewById(R.id.yAxisLabel);

        RotateAnimation rotate= (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotation);
        yAxisLabel.setAnimation(rotate);

        trendsChart.setData(numberOfParkings);

        trendsChart.setTouchEnabled(true);
        trendsChart.setDragEnabled(true);
        trendsChart.setScaleEnabled(true);

        barDataSet.setBarSpacePercent(75f);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        if (text.equals("Monday")) {
            earlyMorning.setVal(5f);
            lateMorning.setVal(20f);
            earlyAfternoon.setVal(30f);
            lateAfternoon.setVal(12f);
        } else if (text.equals("Tuesday")) {
            earlyMorning.setVal(12f);
            lateMorning.setVal(45f);
            earlyAfternoon.setVal(25f);
            lateAfternoon.setVal(19f);
        } else if (text.equals("Wednesday")) {
            earlyMorning.setVal(21f);
            lateMorning.setVal(3f);
            earlyAfternoon.setVal(5f);
            lateAfternoon.setVal(29f);
        } else if (text.equals("Thursday")) {
            earlyMorning.setVal(5f);
            lateMorning.setVal(15f);
            earlyAfternoon.setVal(3f);
            lateAfternoon.setVal(25f);
        } else if (text.equals("Friday")) {
            earlyMorning.setVal(25f);
            lateMorning.setVal(5f);
            earlyAfternoon.setVal(3f);
            lateAfternoon.setVal(13f);
        }
        trendsChart.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
