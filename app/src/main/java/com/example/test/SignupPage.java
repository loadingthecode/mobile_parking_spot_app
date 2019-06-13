package com.example.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.VibrationEffect;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.os.Vibrator;

public class SignupPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText userEmail, pass, reenterPass;

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        mAuth = FirebaseAuth.getInstance();
        userEmail = (EditText)findViewById(R.id.signUpEmail);
        pass = (EditText)findViewById(R.id.signUpPassword);
        reenterPass = (EditText)findViewById(R.id.signUpReenterPassword);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Create a New Account"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Create New Account" + "</font>"));
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));
    }

    public void userVerifyFields (View view) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        String newEmail = userEmail.getText().toString();
        String newPass = pass.getText().toString();
        String newPassAgain = reenterPass.getText().toString();

        if ((newEmail.isEmpty() || userEmail == null) ||
                (newPass.isEmpty() || newPass == null) ||
                    (newPassAgain.isEmpty() || newPassAgain == null)) {
            vibrateHelper(v);
            Toast.makeText(SignupPage.this, "Please make sure the information is correct.",
                    Toast.LENGTH_SHORT).show();
        } else {
            createAccount(newEmail, newPass, newPassAgain);
        }
    }

    private void createAccount(String email, String password, String reenteredPass) {
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        // Allows only users with a valid Rollins email
        // to sign up for the app
        final String REGEX = "^[a-z0-9]+@rollins.edu$";
        if (email.matches(REGEX) &&
                password.equals(reenteredPass)) {

            // [START create_user_with_email]
            // actual creation of account with email and
            // password
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Sign in success, update UI with the signed-in user's information
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupPage.this, "Account created. Check your email" +
                                                    " for verification.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    sendEmailVerification();
                                    returnToLoginScreen();
                                }
                        }
                    });
            // [END create_user_with_email]
            // prevents unauthorized users from using app
        } else if (! password.equals(reenteredPass)) {
            vibrateHelper(v);
            Toast.makeText(SignupPage.this, "Passwords do not match. Please try again.",
                    Toast.LENGTH_SHORT).show();
        } else {
            vibrateHelper(v);
            Toast.makeText(SignupPage.this, "Please enter a valid Rollins email address.",
                    Toast.LENGTH_SHORT).show();
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

    // after successfully sending a password reset email
    // automatically returns to login screen
    public void returnToLoginScreen() {
        Intent intent = new Intent(SignupPage.this, Login.class);
        startActivity(intent);
    }

    private void sendEmailVerification() {
        // Disable button


        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // [START_EXCLUDE]
                        // Re-enable button

                        Log.v( "SSSS", String.valueOf(user.isEmailVerified()));

                        // [END_EXCLUDE]
                    }
                });
        // [END send_email_verification]
    }

    public void clickForSignUp(View view) {
        createAccount(userEmail.getText().toString(), pass.getText().toString(), reenterPass.getText().toString());
    }
}
