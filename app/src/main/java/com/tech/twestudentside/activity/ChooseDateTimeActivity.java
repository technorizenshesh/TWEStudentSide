package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class ChooseDateTimeActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date_time);
    }

    public void ContinueDateAndTimeId(View view) {
        startActivity(new Intent(this, LessonSummaryActivity.class));
    }

    public void backDate(View view) {
        onBackPressed();
        finish();
    }
}
