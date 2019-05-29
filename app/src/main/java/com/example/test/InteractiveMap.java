package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InteractiveMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Parking Lot Map"); // set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // adds back arrow
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_map);
    }
}
