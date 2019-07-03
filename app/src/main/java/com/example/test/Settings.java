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

import com.google.firebase.messaging.FirebaseMessaging;

public class Settings extends AppCompatActivity {

    private Button saveButton;

    protected static Switch toggleNotifsSwitch;

    protected static SharedPreferences settingsPrefs;
    protected static final String PREFS_NAME = "PrefSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Settings"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Settings" + "</font>"));

        toggleNotifsSwitch = findViewById(R.id.notificationsSwitch);

        settingsPrefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        final SharedPreferences.Editor editor = settingsPrefs.edit();
        toggleNotifsSwitch.setChecked(settingsPrefs.getBoolean(PREFS_NAME, true));
        toggleNotifsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("alerts");
                    editor.putBoolean(PREFS_NAME, true);
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("alerts");
                    editor.putBoolean(PREFS_NAME, false);
                }
                editor.commit();
            }
        });
    }
}