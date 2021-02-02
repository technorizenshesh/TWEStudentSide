package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class PurchasePlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_plan);
    }

    public void backPressPurchase(View view) {
        onBackPressed();
        finish();
    }

    public void purchasePlanInit(View view) {
        startActivity(new Intent(PurchasePlanActivity.this,LessonSummaryActivity.class));


    }
}