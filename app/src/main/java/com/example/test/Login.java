package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar bar = getSupportActionBar();

        bar.setTitle("Login"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD700")));

        mAuth = FirebaseAuth.getInstance();
        email = (EditText)findViewById(R.id.signInEmail);
        pass = (EditText)findViewById(R.id.signInPassword);
    }

    public void clickToSignup(View view) {
        Intent intent = new Intent(Login.this, SignupPage.class);
        startActivity(intent);
    }

    private void signIn(String email, String pass) {

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user.isEmailVerified()) {
                                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(Login.this, MainActivity.class);
                                startActivity(intent);
                            }

                            else {
                                Toast.makeText(Login.this, "Please check your email and verify your account.",
                                        Toast.LENGTH_SHORT).show();
                            }

                        } else {

                            Toast.makeText(Login.this, "Login Failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {
                            Toast.makeText(Login.this, "Your credentials don't match any account information.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // [END_EXCLUDE]
                    }
                });
        // [END sign_in_with_email]
    }

    public void userLogin (View view) {
        signIn(email.getText().toString(), pass.getText().toString());
    }
}
