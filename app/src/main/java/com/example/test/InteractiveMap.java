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

    String [] lotList={"Alfonds", "SunTrust"};

    private TextView lightLevel;

    DatabaseReference databaseSensors;
    private static final String TAG = "AlfondsMap";

    //private ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_map);

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

                // counts the number of children starting from root
                //long childrenCount = dataSnapshot.getChildrenCount();

                // gets the actual light value of a sensor
                double value = (double) dataSnapshot.child("Sensor3").child("light").getValue();

                // outputs a message of current light level
                //Toast.makeText(InteractiveMap.this, "Light is " + value, Toast.LENGTH_SHORT).show();

                String stringValue = Double.toString(value);

                lightLevel.setText(stringValue);

                //Log.d(TAG, "Value is: " + light);
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
