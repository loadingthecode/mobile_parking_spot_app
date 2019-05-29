package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BugReport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Bug Report"); // set actionbar title
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // adds back arrow

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);
    }
}
