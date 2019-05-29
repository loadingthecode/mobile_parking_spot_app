package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSettings(View view) {
        Intent settingsIntent = new Intent(this, Settings.class);
        startActivity(settingsIntent);
    }

    public void goToBugReport(View view) {
        Intent bugReportIntent = new Intent(this, BugReport.class);
        startActivity(bugReportIntent);
    }

    public void goToParkingTrends(View view) {
        Intent parkingTrendsIntent = new Intent(this, ParkingTrends.class);
        startActivity(parkingTrendsIntent);
    }

    public void goToHelp(View view) {
        Intent helpIntent = new Intent(this, Help.class);
        startActivity(helpIntent);
    }

    public void goToInteractiveMap(View view) {
        Intent mapIntent = new Intent(this, InteractiveMap.class);
        startActivity(mapIntent);
    }
}
