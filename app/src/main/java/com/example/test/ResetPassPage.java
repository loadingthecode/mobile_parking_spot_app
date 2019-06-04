package com.example.test;

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
import android.widget.Toast;

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

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0071ba")));

        mAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.signInEmail_reset);
    }

    public void userResetPassRequest (View view) {
        clickToSendPasswordReset(email.getText().toString());
    }

    // after successfully sending a password reset email
    // automatically returns to login screen
    public void returnToLoginScreen() {
        Intent intent = new Intent(ResetPassPage.this, Login.class);
        startActivity(intent);
    }

    public void clickToSendPasswordReset(String email) {
        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "Email sent.");
                            Toast.makeText(ResetPassPage.this, "Password reset email sent. Please check your email.",
                                    Toast.LENGTH_LONG).show();
                            returnToLoginScreen();
                        }
                        else {
                            Toast.makeText(ResetPassPage.this, "Email does not exist. Please try again.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
