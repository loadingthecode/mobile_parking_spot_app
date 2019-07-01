package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
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
import java.util.List;

public class ParkingTrends extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // creating the actual bar graph
    BarChart trendsChart;

    // creating the individual bar entries
    ArrayList<BarEntry> barEntries = new ArrayList<>();
    // initializing a bar data set using the bar entries
    // and labeling the x-axis Time of Day
    BarDataSet barDataSet = new BarDataSet(barEntries, "Time of Day");

    // creating the bar entries for each time period
    BarEntry earlyMorning;
    BarEntry lateMorning;
    BarEntry earlyAfternoon;
    BarEntry lateAfternoon;

    // creating a drop-down menu for each day of the week
    Spinner spinner;

    // initializes a spinner to the correct day list
    // and appearance
    public void initSpinner() {

        // initializing the drop-down menu to the xml
        // drop-down menu in the activity page
        spinner = findViewById(R.id.trendsDayChooser);

        // initializing an array adapter to a pre-defined array
        // of days of the week in the strings.xml file
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.dayOfWeek, R.layout.spinner_item);

        // setting the physical appearance of the drop-down menu
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // setting the drop-down menu's list of contents to adapter
        spinner.setAdapter(adapter);

        // setting a callback to be invoked to the class when selected
        spinner.setOnItemSelectedListener(this);
    }

    // setting settings for the chart
    public void setChartAttributes() {

        // links the bar chart to the physical bar chart in the xml
        trendsChart = (BarChart) findViewById(R.id.trendsGraph);

        // setting up the chart's various attributes
        trendsChart.setDrawBarShadow(false);
        trendsChart.setDrawValueAboveBar(true);
        trendsChart.setMaxVisibleValueCount(50);
        trendsChart.setDrawGridBackground(true);
        trendsChart.setTouchEnabled(true);
        trendsChart.setDragEnabled(true);
        trendsChart.setScaleEnabled(true);
        trendsChart.getXAxis().setSpaceBetweenLabels(2); // edit the space between labels
        barDataSet.setBarSpacePercent(75f);

        // sets the theme of the chart
        int [] colors = {Color.rgb(0,0,128), Color.rgb(120, 178, 255),
                Color.rgb(0, 0, 255), Color.rgb(153, 204, 255)};

        List<Integer> myColors = ColorTemplate.createColors(colors);

        barDataSet.setColors(myColors);// from barchart video indian speaker
    }

    // setting each bar entries' default values
    public void setBarEntries() {

        // sets the chart's default values
        earlyMorning = new BarEntry(65f, 0);
        lateMorning = new BarEntry(10f, 1);
        earlyAfternoon = new BarEntry(26f, 2);
        lateAfternoon = new BarEntry(3f, 3);

        // adds the various time periods to the x axis
        barEntries.add(earlyMorning);
        barEntries.add(lateMorning);
        barEntries.add(earlyAfternoon);
        barEntries.add(lateAfternoon);
    }

    // adding the x axis labels to the bar chart
    public void setBarData() {

        // storing different time periods in an arraylist
        ArrayList<String> theTimes = new ArrayList<String>();
        theTimes.add("Before 9AM");
        theTimes.add("9AM-12PM");
        theTimes.add("12PM-3PM");
        theTimes.add("3PM-5PM");

        // initializes the labels of each bar entries to various time ranges
        BarData numberOfParkings = new BarData(theTimes, barDataSet);

        // links the bar data to the bar chart
        trendsChart.setData(numberOfParkings);
    }

    // sets the label for the y-axis
    public void setYAxisLabel() {

        // creating a TextView object for the y-axis label
        TextView yAxisLabel = (TextView)findViewById(R.id.yAxisLabel);
        RotateAnimation rotate= (RotateAnimation) AnimationUtils.loadAnimation(this,R.anim.rotation);
        yAxisLabel.setAnimation(rotate);
    }

    // changes the graph to the parking trends of the selected day
    public void changeChartVisuals(String t) {

        // sets # of available parkings at each time of each day
        if (t.equals("Monday")) {
            earlyMorning.setVal(5f);
            lateMorning.setVal(20f);
            earlyAfternoon.setVal(30f);
            lateAfternoon.setVal(12f);
        } else if (t.equals("Tuesday")) {
            earlyMorning.setVal(12f);
            lateMorning.setVal(45f);
            earlyAfternoon.setVal(25f);
            lateAfternoon.setVal(19f);
        } else if (t.equals("Wednesday")) {
            earlyMorning.setVal(21f);
            lateMorning.setVal(3f);
            earlyAfternoon.setVal(5f);
            lateAfternoon.setVal(29f);
        } else if (t.equals("Thursday")) {
            earlyMorning.setVal(5f);
            lateMorning.setVal(15f);
            earlyAfternoon.setVal(3f);
            lateAfternoon.setVal(25f);
        } else if (t.equals("Friday")) {
            earlyMorning.setVal(25f);
            lateMorning.setVal(5f);
            earlyAfternoon.setVal(3f);
            lateAfternoon.setVal(13f);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parking_trends);

        // adding an actionbar to the top of the screen
        ActionBar bar = getSupportActionBar();
        bar.setTitle("Parking Trends"); // set actionbar title
        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Parking Trends" + "</font>"));

        // initializing the drop-down menu to the xml
        // drop-down menu in the activity page
        initSpinner();

        // sets the chart's default attributes e.g. no bar shadow
        setChartAttributes();

        // adds the various time ranges as bar entries for the chart
        setBarEntries();

        // sets the number of parkings available for each time period
        setBarData();

        // sets the label for the y-axis
        setYAxisLabel();
    }

    // changes the graph visuals based on day selected from the drop-down menu
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // stores the day name selected from the drop-down menu into a string
        String text = parent.getItemAtPosition(position).toString();

        // changes the graph to the parking trends of the selected day
        changeChartVisuals(text);

        // gives visual confirmation of day selected
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        // updates the chart
        trendsChart.invalidate();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}


//String [] dayList={"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};