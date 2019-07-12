package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BugReport extends AppCompatActivity {
    //private EditText to;
    private EditText subject;
    private EditText bugReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bug_report);

        ActionBar bar = getSupportActionBar();

        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setTitle(Html.fromHtml("<font color=\"#0071ba\">" + "Report a Bug" + "</font>"));

        subject = findViewById(R.id.userSubject);
        bugReport = findViewById(R.id.userMessage);

        Button bugReport = findViewById(R.id.sendBugReport);

        bugReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }

    // method used to email a bug report to our address
    public void sendEmail() {

        // creating an array of recipients for scalability
        String[] mailTo = new String[]{"parkingappreports@gmail.com"};

        // storing the subject and message in Strings
        String subjectLine = subject.getText().toString();
        String message = bugReport.getText().toString();

        // responsible for sending the email
        Intent intent = new Intent(Intent.ACTION_SEND);

        // putting our email address into the "to" line of their mail client
        intent.putExtra(Intent.EXTRA_EMAIL, mailTo);

        // putting the user's subject into their mail client's subject line
        intent.putExtra(Intent.EXTRA_SUBJECT, subjectLine);

        // putting the user's message into their mail client's message box
        intent.putExtra(Intent.EXTRA_TEXT, message);

        // standard email address format
        intent.setType("message/rfc822");

        // gives the user a choice of which mail client they'd like to use
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    public void returnToLoginScreen() {
        Intent intent = new Intent(BugReport.this, MainActivity.class);
        startActivity(intent);
    }
}
