package com.example.test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Help"); // set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // adds back arrow
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

    }
}
