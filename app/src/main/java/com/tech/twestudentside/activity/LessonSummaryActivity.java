package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class LessonSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_summary);
    }

    public void summaryBackInit(View view) {
        onBackPressed();
        finish();
    }

    public void checkout(View view) {
        startActivity(new Intent(LessonSummaryActivity.this, ChoosePaymentActivity.class));
    }
}