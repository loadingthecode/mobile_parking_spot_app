package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
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

import java.util.ArrayList;
import java.util.List;

public class ChooseGarage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    // creating a drop-down menu for each day of the week
    Spinner spinner;

    // initializes a spinner to the correct day list
    // and appearance
    public void initSpinner() {

        // initializing the drop-down menu to the xml
        // drop-down menu in the activity page
        spinner = findViewById(R.id.lotChooser);

        // initializing an array adapter to a pre-defined array
        // of days of the week in the strings.xml file
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.parkingLotList, R.layout.spinner_item);

        // setting the physical appearance of the drop-down menu
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // setting the drop-down menu's list of contents to adapter
        spinner.setAdapter(adapter);

        // setting a callback to be invoked to the class when selected
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_garage);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Choose a Parking Lot"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + getString(R.string.app_name) + "</font>"));

        // initializing the drop-down menu to the xml
        // drop-down menu in the activity page
        initSpinner();
    }

    public void goToAlfonds() {
        Intent alfondsIntent = new Intent(this, AlfondsMap.class);
        startActivity(alfondsIntent);
    }

    public void goToSunTrust() {
        Intent suntrustIntent = new Intent(this, SuntrustMap.class);
        startActivity(suntrustIntent);
    }

    public void goToSutton() {
        Intent suttonIntent = new Intent(this, SuttonMap.class);
        startActivity(suttonIntent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // stores the day name selected from the drop-down menu into a string
        String text = parent.getItemAtPosition(position).toString();

        if (text.equals("Alfonds")) {
            goToAlfonds();
        } else if (text.equals("SunTrust")) {
            goToSunTrust();
        } else if (text.equals("Sutton")) {
            goToSutton();
        }

        parent.setSelection(0);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        parent.setSelection(0);
    }
}
