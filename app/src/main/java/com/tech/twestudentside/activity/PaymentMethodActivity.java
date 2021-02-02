package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class PaymentMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
    }

    public void back_payment(View view) {
        onBackPressed();
        finish();
    }

    public void paymentMethodBtn(View view) {
        startActivity(new Intent(PaymentMethodActivity.this,AddPaymentMethodsActivity.class));
        finish();
    }
}