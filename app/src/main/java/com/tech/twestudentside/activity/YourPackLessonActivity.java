package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tech.twestudentside.R;

public class YourPackLessonActivity extends AppCompatActivity {
    ImageView iv_back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_pack_lesson);


    }


    public void tutorMapInit(View view) {
        startActivity(new Intent(YourPackLessonActivity.this,TutorTrackngActivity.class));
    }
}