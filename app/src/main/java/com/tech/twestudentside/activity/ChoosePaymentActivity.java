package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class ChoosePaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);
    }

    public void ChoosePaymentCheckout(View view) {
        startActivity(new Intent(ChoosePaymentActivity.this,PaymentMethodActivity.class));
    }

    public void choosePaymentBack(View view) {
        onBackPressed();
        finish();
    }
}