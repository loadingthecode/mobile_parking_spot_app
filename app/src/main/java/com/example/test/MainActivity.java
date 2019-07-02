package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.MenuItem;
import android.view.Menu;
import android.widget.EditText;

import com.google.firebase.messaging.RemoteMessage;

import java.net.InterfaceAddress;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Rollins Parking App"); // set actionbar title

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + getString(R.string.app_name) + "</font>"));
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));
    }

    public void goToDebug(View view) {
        Intent debugIntent = new Intent(this, SensorDebug.class);
        startActivity(debugIntent);
    }

    public void goToLogin(View view) {
        Intent loginIntent = new Intent(this, Login.class);
        startActivity(loginIntent);
        Login.prefs.edit().clear().apply();
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

    public void goToGarageChooser(View view) {
        Intent chooseGarageIntent = new Intent(this, ChooseGarage.class);
        startActivity(chooseGarageIntent);
    }
}
