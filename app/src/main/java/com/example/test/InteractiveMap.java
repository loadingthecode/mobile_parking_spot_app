package com.example.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    private static long numOfSensors;

    private static ArrayList<Indicator> indicatorList = new ArrayList<>();
    private static ImageView[] indicatorImages = new ImageView[4];

    // debugging light levels;
    // delete later
    private static ArrayList<TextView> debugTextViews = new ArrayList<>();

    // for debugging
    private TextView sensorCount;

    // creating the database object
    DatabaseReference databaseSensors;

    // getting the number of sensors in database
    // converting it to a string for debugging
    public String getNumOfSensors(DataSnapshot ds) {
        numOfSensors = ds.getChildrenCount();
        String convertedCount = Long.toString(numOfSensors);
        return convertedCount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_map);

        // matching the # of sensors to a TextView for debugging
        sensorCount = findViewById(R.id.numberOfSensors);

        databaseSensors = FirebaseDatabase.getInstance().getReference("");

        // initialization of the indicator and textview lists
        // populating the indicator objects with sensorID and light levels
        for (int i = 0; i < indicatorImages.length; i++) {
            // getting the spotID and matching it by index with
            // each sensor
            String spotID = "sensor" + i;
            Indicator spot = new Indicator(spotID, 0);
            indicatorList.add(spot);

            // storing each xml imageview sensor into an int
            // creating an java imageview object and putting the xml imageview into it
            // storing that java imageview into an imageview array
            // going through indicator array and setting each indicator's imageview status to
            // its iterative counterpart in the imageview array
            int imageId = getResources().getIdentifier("sensor" + i, "id", getPackageName());
            ImageView iv = (ImageView) findViewById(imageId);
            indicatorImages[i] = iv;
            indicatorList.get(i).setStatus(indicatorImages[i]);

            // debugging light levels for all 3 indicators
            // storing each xml textview object into an int
            // creating a java textview object and putting the xml textview into it
            // adding each textview to a list of textviews
            int lightLevelDebugId = getResources().getIdentifier("lightLevel" + i, "id", getPackageName());
            TextView tv = (TextView) findViewById(lightLevelDebugId);
            debugTextViews.add(tv);
        }
    }

    // method that constantly updates parking indicator
    // based on firebase snapshot
    public void takeSnapshot(DataSnapshot ds) {

        for (int i = 0; i < indicatorImages.length; i++) {
            // sets the light value of each 
            indicatorList.get(i).setLight((long) ds.child("Sensor" + i).child("light").getValue());

            // stores current iteration's light level
            // in a long
            long sensorLightLevel = indicatorList.get(i).getLight();

            // for debugging:
            // visualizes real-time light data for each sensor via textview
            debugTextViews.get(i).setText(Long.toString(sensorLightLevel));

            if (sensorLightLevel < 100) {
                indicatorImages[i].setImageResource(R.drawable.parking_unavailable);
            } else {
                indicatorImages[i].setImageResource(R.drawable.parking_available);
            }
        }
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

                // method that constantly updates parking indicator
                // based on firebase snapshot
                takeSnapshot(dataSnapshot);
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
