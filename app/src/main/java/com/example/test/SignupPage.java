package com.example.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.MatchResult;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText userEmail, pass;


    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);
        mAuth = FirebaseAuth.getInstance();
        userEmail = (EditText)findViewById(R.id.signUpEmail);
        pass = (EditText)findViewById(R.id.signUpPassword);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Create a New Account"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));
    }

    private void createAccount(String email, String password) {

        final String REGEX = "^[a-z0-9]+@rollins.edu$";

        if (userEmail.getText().toString().matches(REGEX)) {

            // [START create_user_with_email]
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Sign in success, update UI with the signed-in user's information
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignupPage.this, "Account created. Check your email" +
                                                    "for verification.",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    sendEmailVerification();
                                }
                        }
                    });
            // [END create_user_with_email]
        } else {
            Toast.makeText(SignupPage.this, "Please enter a valid Rollins email address.",
                    Toast.LENGTH_SHORT).show();
        }
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
        createAccount(userEmail.getText().toString(), pass.getText().toString());
    }
}
