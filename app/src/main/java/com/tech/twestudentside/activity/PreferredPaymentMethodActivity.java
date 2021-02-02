package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class PreferredPaymentMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_payment_method);
    }

    public void PrePaymentMethod(View view) {
        onBackPressed();
        finish();
    }
}