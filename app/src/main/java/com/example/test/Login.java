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
    protected static SharedPreferences prefs;
    private static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Login"); // set actionbar title

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Login" + "</font>"));
        //bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        stayLoggedIn = (CheckBox) findViewById(R.id.rememberMe);


        mAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.signInEmail);
        pass = (EditText)findViewById(R.id.signInPassword);

        getPreferencesData();

        if (stayLoggedIn.isChecked()) {
            goToHomeScreen();
        }
    }

    private void getPreferencesData() {
        SharedPreferences sp = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        if(sp.contains("pref_check")) {
            Boolean b = sp.getBoolean("pref_check", false);
            stayLoggedIn.setChecked(b);
        }
    }

    // checks if the user wants to stay logged in
    public void checkLoginSaved() {
        Boolean boxIsChecked = stayLoggedIn.isChecked();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("pref_check", boxIsChecked);
        editor.apply();
    }

    // if user clicks "CREATE ACCOUNT" button
    // sends to SignupPage activity
    public void clickToSignup(View view) {
        Intent intent = new Intent(Login.this, SignupPage.class);
        startActivity(intent);
    }

    public void clickToGoToPasswordResetPage(View view) {
        Intent intent = new Intent(Login.this, ResetPassPage.class);
        startActivity(intent);
    }

    public void goToHomeScreen() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

    private void signIn(String email, String pass) {

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user.isEmailVerified()) {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                if (stayLoggedIn.isChecked()) {
                                    checkLoginSaved();
                                } else {
                                    prefs.edit().clear().apply();
                                }

                                goToHomeScreen();
                            }

                            else {
                                vibrateHelper(v);
                                Toast.makeText(Login.this, "Please check your email and verify your account.",
                                        Toast.LENGTH_SHORT).show();

                                stayLoggedIn.setChecked(false);
                                checkLoginSaved();
                            }

                        } else {
                            vibrateHelper(v);
                            Toast.makeText(Login.this, "Your credentials don't match any account information.",
                                    Toast.LENGTH_SHORT).show();

                            stayLoggedIn.setChecked(false);
                            checkLoginSaved();
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    public void userLogin (View view) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        if ((userEmail.isEmpty() || userEmail == null) ||
                (userPass.isEmpty() || userPass == null)) {
            vibrateHelper(v);
            Toast.makeText(Login.this, "Please enter your email and password.",
                    Toast.LENGTH_SHORT).show();
            stayLoggedIn.setChecked(false);
            checkLoginSaved();
        } else {
            signIn(userEmail, userPass);
        }
    }

    public void vibrateHelper(Vibrator vib) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vib.vibrate(500);
        }
    }
}
