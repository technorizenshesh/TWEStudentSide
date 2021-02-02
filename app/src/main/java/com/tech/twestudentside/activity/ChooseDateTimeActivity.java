package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class ChooseDateTimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date_time);
    }

    public void ContinueDateAndTimeId(View view) {

        startActivity(new Intent(ChooseDateTimeActivity.this, LessonSummaryActivity.class));

    }

    public void backDate(View view) {
        onBackPressed();
        finish();
    }
}