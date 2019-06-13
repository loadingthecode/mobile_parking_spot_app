package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar bar = getSupportActionBar();

        bar.setTitle("Help"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0071ba")));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

    }
}
