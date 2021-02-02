package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;

public class AddPaymentMethodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_payment_methods);
    }

    public void backAddpayment(View view) {
        onBackPressed();
        finish();
    }

    public void PaymentSuccessfull(View view) {
        startActivity(new Intent(AddPaymentMethodsActivity.this, RatingActivity.class));
        finish();
    }
}