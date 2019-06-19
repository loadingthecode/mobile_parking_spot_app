package com.example.test;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InteractiveMap extends AppCompatActivity {

    private static final String TAG = "InteractiveMap";

    // Firebase only takes longs and doubles, not ints
    private static long lightMeasurement;
    private static long numOfSensors;
    private static ArrayList<Indicator> indicatorList = new ArrayList<>();
    private static ImageView[] indicatorImages = new ImageView[3];

    // for debugging
    private TextView lightLevel;
    private TextView sensorCount;
    private ImageView parkingIndicator;

    // creating the database object
    DatabaseReference databaseSensors;

    // method that constantly updates parking indicator
    // based on firebase snapshot
    public void takeSnapshot(long light, DataSnapshot ds) {
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

        parkingIndicator = findViewById(R.id.sensor0);
        lightLevel = findViewById(R.id.lightLevel);
        sensorCount = findViewById(R.id.numberOfSensors);

        databaseSensors = FirebaseDatabase.getInstance().getReference("");

        for (int i = 0; i < numOfSensors; i++) {
            String spotID = "sensor" + i;
            Indicator spot = new Indicator(R.id.sensor0, spotID, 0);
            indicatorList.add(spot);
        }

        //TODO: focus here
        ImageView sensor2 =(ImageView) findViewById(R.id.sensor2);

        for (int i = 0; i < indicatorImages.length; i++) {
            int id = getResources().getIdentifier("sensor" + i, "id", getPackageName());
            ImageView iv = (ImageView) findViewById(id);
            iv.setImageResource(R.drawable.map);
            indicatorImages[i] = iv;
            indicatorImages[i].setImageResource(R.drawable.new_parking_icon);
        }
        //
    }

    // getting the number of sensors in database
    // converting it to a string for debugging
    public String getNumOfSensors(DataSnapshot ds) {
        numOfSensors = ds.getChildrenCount();
        String convertedCount = Long.toString(numOfSensors);
        return convertedCount;
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

                // getting the number of sensors in database
                // converting it to a string for debugging
                sensorCount.setText(getNumOfSensors(dataSnapshot));

                // gets the actual light value of a sensor
                // make sure to uncomment this
                lightMeasurement = (long) dataSnapshot.child("Sensor0").child("light").getValue();

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
