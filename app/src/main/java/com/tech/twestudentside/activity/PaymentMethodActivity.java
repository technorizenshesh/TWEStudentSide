package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class PaymentMethodActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
    }

    public void back_payment(View view) {
        onBackPressed();
        finish();
    }

    public void paymentMethodBtn(View view) {
        startActivity(new Intent(this, AddPaymentMethodsActivity.class));
        finish();
    }
}
