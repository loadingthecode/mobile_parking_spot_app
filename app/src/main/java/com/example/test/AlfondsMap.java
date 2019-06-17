package com.example.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class AlfondsMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar bar = getSupportActionBar();

        bar.setTitle("Alfonds Parking Lot"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Parking Lot Map" + "</font>"));
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfonds_lot_map);
    }
}