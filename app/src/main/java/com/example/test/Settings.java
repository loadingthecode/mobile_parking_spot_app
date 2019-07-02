package com.example.test;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class Settings extends AppCompatActivity {

    private Switch defaultHomeSwitch;
    private Button saveButton;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String DEFAULTHOMESWITCH = "defaultHomeSwitch";

    private boolean switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Settings"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Settings" + "</font>"));
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        saveButton = (Button) findViewById(R.id.saveButton);
        defaultHomeSwitch = (Switch)findViewById(R.id.defaultHomeSwitch);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        /*defaultHomeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("com.example.xyz", MODE_PRIVATE).edit();
                if (isChecked) {
                    mapHomeScreenSwitchChecked = true;
                } else {
                    mapHomeScreenSwitchChecked = false;
                }
            }
        });*/

        loadData();
        updateViews();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(DEFAULTHOMESWITCH, defaultHomeSwitch.isChecked());

        editor.apply();

        Toast.makeText(this, "Settings have been saved.", Toast.LENGTH_SHORT).show();

    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(DEFAULTHOMESWITCH, false);
    }

    public void updateViews() {
        defaultHomeSwitch.setChecked(switchOnOff);
    }
}
