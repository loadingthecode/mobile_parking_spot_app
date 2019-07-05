package com.example.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AlfondsMap extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfonds_lot_map);

        ActionBar bar = getSupportActionBar();

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Alfonds Lot" + "</font>"));
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow
    }

    public void setAlfondsAsFave(View view) {
        MainActivity.favLot = "Alfonds";

        // gets the sharedpreferences in the main activity at the index of favorite lot
        SharedPreferences favoriteLot = getSharedPreferences(MainActivity.PREFS_MAIN, 0);
        // gets the ability to modify saved data in the shared preferences
        SharedPreferences.Editor editor = favoriteLot.edit();
        // updates the favorite lot string with the current map name
        editor.putString("favLot", MainActivity.favLot);
        // saves favorite lot to the shared preferences
        editor.commit();

        // notifies user of successful operation
        Toast.makeText(AlfondsMap.this, MainActivity.favLot + " has been set as your favorite lot.",
                Toast.LENGTH_SHORT).show();

        // returns user to home screen
        goToHomeScreen();
    }
    // returns user to home screen
    public void goToHomeScreen () {
        Intent intent = new Intent(AlfondsMap.this, MainActivity.class);
        startActivity(intent);
    }
}