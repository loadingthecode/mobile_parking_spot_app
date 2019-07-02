package com.example.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
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

    DatabaseReference databaseSensors;
    private static final String TAG = "AlfondsMap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alfonds_lot_map);

        databaseSensors = FirebaseDatabase.getInstance().getReference("parkingapp-c075d");

        // Read from the database
        databaseSensors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                Toast.makeText(AlfondsMap.this, "Value is " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(AlfondsMap.this, "Error", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Alfonds Parking Lot"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Parking Lot Map" + "</font>"));
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

    }
}