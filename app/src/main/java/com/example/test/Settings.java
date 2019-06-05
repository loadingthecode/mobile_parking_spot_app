package com.example.test;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class Settings extends AppCompatActivity {

    public static boolean mapHomeScreenSwitchChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Settings"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0071ba")));

        Switch setHome = (Switch)findViewById(R.id.defaultHomeSwitch);

        setHome.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("com.example.xyz", MODE_PRIVATE).edit();
                if (isChecked) {
                    mapHomeScreenSwitchChecked = true;
                    editor.putBoolean("mapSwitchedOn", true);
                    editor.commit();
                } else {
                    mapHomeScreenSwitchChecked = false;
                    editor.putBoolean("mapSwitchedOn", false);
                    editor.commit();
                }
            }
        });

    }
}
