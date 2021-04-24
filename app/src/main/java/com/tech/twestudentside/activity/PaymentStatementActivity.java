package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class PaymentStatementActivity extends AppCompatActivity {
    FrameLayout frameLayout_payment;
    ImageView ivBack;
    TextView totalTab_txtId;
    TextView upcoming_txtId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_statement);
    }

    public void OliviaParkerInit(View view) {
        startActivity(new Intent(this, TutorPaymentDetailsActivity.class));
    }
}
