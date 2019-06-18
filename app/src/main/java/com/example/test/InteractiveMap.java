package com.example.test;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InteractiveMap extends AppCompatActivity {

    private static final String TAG = "AlfondsMap";

    // Firebase only takes longs and doubles, not ints
    public static long lightMeasurement;

    private TextView lightLevel;
    public ImageView parkingIndicator;

    DatabaseReference databaseSensors;

    // method that constantly updates parking indicator
    // based on firebase snapshot
    public void takeSnapshot(long light, DataSnapshot ds) {
        // gets the actual light value of a sensor
        light = (long) ds.child("Sensor3").child("light").getValue();

        // For debugging purposes
        // converting light # to viewable string
        String stringValue = Double.toString(light);

        // setting the viewable text to the String light value
        lightLevel.setText(stringValue);

        // parking spot turns red if the measured
        // light is less than 100 lumens
        if (light < 100.00) {
            parkingIndicator.setImageResource(R.drawable.parking_unavailable);
        } else {
            parkingIndicator.setImageResource(R.drawable.parking_available);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_map);

        parkingIndicator = findViewById(R.id.parkingIndicator);

        databaseSensors = FirebaseDatabase.getInstance().getReference("");
        lightLevel = findViewById(R.id.lightLevel);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Read from the database
        databaseSensors.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                // gets the actual light value of a sensor
                lightMeasurement = (long) dataSnapshot.child("Sensor3").child("light").getValue();

                // method that constantly updates parking indicator
                // based on firebase snapshot
                takeSnapshot(lightMeasurement, dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(InteractiveMap.this, "Error", Toast.LENGTH_SHORT).show();
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }


       /* Spinner spinner = findViewById(R.id.lotChooser);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.parkingLotList, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Choose a parking location");*/
}
