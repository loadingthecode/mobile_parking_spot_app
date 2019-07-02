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
import android.widget.Button;
import android.widget.EditText;

public class Help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar bar = getSupportActionBar();

        bar.setTitle("Help"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Help" + "</font>"));
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

    }
}
