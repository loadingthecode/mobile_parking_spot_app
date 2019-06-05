package com.example.test;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        bar.setTitle("Send a Bug Report"); // set actionbar title
        bar.setDisplayHomeAsUpEnabled(true); // adds back arrow

        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0071ba")));

        //to = findViewById(R.id.to);
        subject = findViewById(R.id.userSubject);
        bugReport = findViewById(R.id.userMessage);

        Button bugReport = findViewById(R.id.sendBugReport);

        bugReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    public void sendMail() {
        //String recipientList = to.getText().toString();
        //String[] recipients = recipientList.split(",");

        String[] recipients = new String[]{"parkingappreports@gmail.com"};

        //recipients[0] = "parkingappreports@gmail.com";

        String mailSubject = subject.getText().toString();
        String message = bugReport.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        intent.putExtra(Intent.EXTRA_SUBJECT, mailSubject);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    public void returnToLoginScreen() {
        Intent intent = new Intent(BugReport.this, MainActivity.class);
        startActivity(intent);
    }
}
