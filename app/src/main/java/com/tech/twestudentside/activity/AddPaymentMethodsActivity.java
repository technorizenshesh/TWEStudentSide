package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class AddPaymentMethodsActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_add_payment_methods);
    }

    public void backAddpayment(View view) {
        onBackPressed();
        finish();
    }

    public void PaymentSuccessfull(View view) {
        startActivity(new Intent(this, RatingActivity.class));
        finish();
    }
}
