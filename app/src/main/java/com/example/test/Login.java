package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
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

import java.util.Map;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Login"); // set actionbar title

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        mAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.signInEmail);
        pass = (EditText)findViewById(R.id.signInPassword);
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
        if (Settings.DEFAULTHOMESWITCH.equals(true)) {
            Intent intent = new Intent(Login.this, InteractiveMap.class);
            startActivity(intent);
            Toast.makeText(this,"Switch on", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
        }
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

                                goToHomeScreen();
                            }

                            else {
                                vibrateHelper(v);
                                Toast.makeText(Login.this, "Please check your email and verify your account.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            vibrateHelper(v);
                            Toast.makeText(Login.this, "Your credentials don't match any account information.",
                                    Toast.LENGTH_SHORT).show();
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
