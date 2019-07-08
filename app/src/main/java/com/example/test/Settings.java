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

    protected static Switch toggleNotifsSwitch;

    protected static SharedPreferences notifPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ActionBar bar = getSupportActionBar();

        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Settings" + "</font>"));

        // linking the java switch to the xml one
        toggleNotifsSwitch = findViewById(R.id.notificationsSwitch);

        // a shared preference for turning notifications on or off
        notifPref = getSharedPreferences(MainActivity.PREFS_MAIN, 0);

        // checks if the user wanted notifications on or not
        // and saves the preference
        checkNotifSwitch();

        // recalls what the user's preference was for notifications
        getNotifSwitch();

    }

    // recalls what the user chose for receiving notifications
    public void getNotifSwitch() {
        // sets the switch to whatever value was stored in the notifs preference
        toggleNotifsSwitch.setChecked(notifPref.getBoolean("notifs", false));
    }

    // determines if the user gets notifications or not
    public void checkNotifSwitch() {

        final SharedPreferences.Editor editor = notifPref.edit();

        // checks to see if the user has ticked the notifications on
        // and subscribes them to firebase alerts
        toggleNotifsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    FirebaseMessaging.getInstance().subscribeToTopic("alerts");
                    editor.putBoolean("notifs", true);
                } else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("alerts");
                    editor.putBoolean("notifs", false);
                }
                editor.apply();
            }
        });
    }
}