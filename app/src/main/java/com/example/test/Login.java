package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import android.os.Vibrator;
import android.os.Build;
import android.os.VibrationEffect;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Map;

import static java.lang.System.in;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, pass;
    private CheckBox stayLoggedIn;

    // creating a shared preference for 'stay logged in'
    protected static SharedPreferences loginPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar bar = getSupportActionBar();

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Login" + "</font>"));

        // setting the stay logged in pref to the main prefs in mainactivity
        loginPrefs = getSharedPreferences(MainActivity.PREFS_MAIN, 0);

        // linking the java checkbox to the physical checkbox
        stayLoggedIn = (CheckBox) findViewById(R.id.rememberMe);

        // getting the current user
        mAuth = FirebaseAuth.getInstance();

        // setting the java account credential fields to their xml counterparts
        email = (EditText) findViewById(R.id.signInEmail);
        pass = (EditText) findViewById(R.id.signInPassword);

        // recalling if the "stay logged in" checkbox was ticked or not
        getPreferencesData();

        // if stay logged in was checked, skip login screen
        // and go directly to home screen
        if (stayLoggedIn.isChecked()) {
            goToHomeScreen();
        }
    }

    // method to recall if the stay logged in checkbox was ticked or not
    private void getPreferencesData() {
        // storing the current login preference value in a boolean
        Boolean b = loginPrefs.getBoolean("login_pref", false);

        // setting the physical checkbox to either true or false
        stayLoggedIn.setChecked(b);
    }

    // checks if the user wants to stay logged in
    public void checkLoginSaved() {
        Boolean boxIsChecked = stayLoggedIn.isChecked();
        SharedPreferences.Editor editor = loginPrefs.edit();
        editor.putBoolean("login_pref", boxIsChecked);
        editor.apply();
    }

    // goes to account creation page
    public void clickToSignup(View view) {
        Intent intent = new Intent(Login.this, SignupPage.class);
        startActivity(intent);
    }

    // goes to password reset page
    public void clickToGoToPasswordResetPage(View view) {
        Intent intent = new Intent(Login.this, ResetPassPage.class);
        startActivity(intent);
    }

    // goes to homescreen
    public void goToHomeScreen() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    // method that authorizes sign-in to app homescreen
    private void signIn(String email, String pass) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // vibrator functionality for failed authorizations
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        // if Firebase successfully authorizes credentials
                        if (task.isSuccessful()) {

                            // getting the current user
                            FirebaseUser user = mAuth.getCurrentUser();

                            // checks if the user verified their account via email
                            if (user != null) {
                                if (user.isEmailVerified()) {
                                    Toast.makeText(Login.this, "Login Successful",
                                            Toast.LENGTH_SHORT).show();

                                    // checks if the user wanted to stay logged in
                                    if (stayLoggedIn.isChecked()) {
                                        checkLoginSaved();
                                    } else {
                                        // removes login preference if
                                        // they don't want to stay logged in
                                        loginPrefs.edit().remove("login_prefs").apply();
                                    }

                                    // goes to the homescreen
                                    goToHomeScreen();
                                }

                                else {
                                    // vibrates on failed log-in attempt
                                    vibrateHelper(v);

                                    Toast.makeText(Login.this, "Please check " +
                                                    "your email and verify your account.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }

                        } else {
                            // vibrates on failed log-in attempt
                            vibrateHelper(v);
                            Toast.makeText(Login.this, "Your credentials don't " +
                                            "match any account information.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    // checks to make sure user email isn't empty or null before going into main sign-in method
    public void userLogin (View view) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        if ((userEmail.isEmpty() || userEmail == null) ||
                (userPass.isEmpty() || userPass == null)) {
            vibrateHelper(v);
            Toast.makeText(Login.this, "Please enter your email and password.",
                    Toast.LENGTH_SHORT).show();
        } else {
            signIn(userEmail, userPass);
        }
    }

    // helper method for vibration functionality
    public void vibrateHelper(Vibrator vib) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vib.vibrate(500);
        }
    }
}
