package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class PurchasePlanActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_plan);
    }

    public void backPressPurchase(View view) {
        onBackPressed();
        finish();
    }

    public void purchasePlanInit(View view) {
        startActivity(new Intent(this, LessonSummaryActivity.class));
    }
}
