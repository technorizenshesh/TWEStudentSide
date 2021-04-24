package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class RatingActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
    }

    public void backToSucess(View view) {
        startActivity(new Intent(this, HomeActvity.class));
        finish();
    }
}
