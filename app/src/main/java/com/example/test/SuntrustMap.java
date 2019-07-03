package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class SuntrustMap extends AppCompatActivity {

    private Button fave;

    protected static SharedPreferences favePrefs;
    protected static final String PREFS_NAME = "PrefSettings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suntrust_map);

        fave = findViewById(R.id.suntrustFave);
    }

    public void setSunTrustAsFave(View view) {
        MainActivity.favLot = "SunTrust";
    }
}
