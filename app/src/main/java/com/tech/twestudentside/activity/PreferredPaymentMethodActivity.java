package com.tech.twestudentside.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class PreferredPaymentMethodActivity extends AppCompatActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferred_payment_method);
    }

    public void PrePaymentMethod(View view) {
        onBackPressed();
        finish();
    }
}
