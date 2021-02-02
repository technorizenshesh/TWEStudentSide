package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class TutorTrackngActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_trackng);

    }

    public void startInit(View view) {
        startActivity(new Intent(TutorTrackngActivity.this, MyClassActivity.class));

    }
}