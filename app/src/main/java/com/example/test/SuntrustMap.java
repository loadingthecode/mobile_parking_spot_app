package com.example.test;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.View;
import android.widget.Button;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

public class SuntrustMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suntrust_map);

        ActionBar bar = getSupportActionBar();

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "SunTrust Lot" + "</font>"));
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow
    }

    public void setSunTrustAsFave(View view) {
        // sets the favorite lot to current map name
        MainActivity.favLot = "SunTrust";

        // gets the sharedpreferences in the main activity at the index of favorite lot
        SharedPreferences favoriteLot = getSharedPreferences(MainActivity.PREFS_MAIN, 0);
        // gets the ability to modify saved data in the shared preferences
        SharedPreferences.Editor editor = favoriteLot.edit();
        // updates the favorite lot string with the current map name
        editor.putString("favLot", MainActivity.favLot);
        // saves favorite lot to the shared preferences
        editor.commit();

        // notifies user of successful operation
        Toast.makeText(SuntrustMap.this, MainActivity.favLot + " has been set as your favorite lot.",
                Toast.LENGTH_SHORT).show();

        // returns user to home screen
        goToHomeScreen();
    }

    public void goToHomeScreen() {
        Intent intent = new Intent(SuntrustMap.this, MainActivity.class);
        startActivity(intent);
    }
}
