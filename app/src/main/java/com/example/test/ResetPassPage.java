package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.os.Vibrator;
import android.os.Build;
import android.os.VibrationEffect;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPassPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private static final String TAG = "ResetPassPage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass_page);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Reset Password"); // set actionbar title

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Reset Password" + "</font>"));
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        mAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.signInEmail_reset);
    }

    public void userResetPassRequest (View view) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        String desiredEmail = email.getText().toString();

        if (! desiredEmail.isEmpty() && desiredEmail != null) {
            clickToSendPasswordReset(email.getText().toString());
        } else {
            vibrateHelper(v);
            Toast.makeText(ResetPassPage.this, "Please enter an email address.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    // after successfully sending a password reset email
    // automatically returns to login screen
    public void returnToLoginScreen() {
        Intent intent = new Intent(ResetPassPage.this, Login.class);
        startActivity(intent);
    }

    public void vibrateHelper(Vibrator vib) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vib.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            vib.vibrate(500);
        }
    }

    public void clickToSendPasswordReset(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            Toast.makeText(ResetPassPage.this, "Password reset email sent. Please check your email.",
                                    Toast.LENGTH_LONG).show();
                            returnToLoginScreen();
                        }
                        else {
                            vibrateHelper(v);
                            Toast.makeText(ResetPassPage.this, "Email does not exist. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
