package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.tech.twestudentside.PaymentGetway.PaymentCashFreeActivity;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.utils.SessionManager;

public class ChoosePaymentActivity extends AppCompatActivity {

    String Pakage_price = "";
    String PaymentType = "cash";
    private CardView card_cash;
    private CardView card_cash_free;
    private LinearLayout ll_cash;
    private LinearLayout ll_cashFree;
    private ProgressBar progressBar;
    private RadioButton radi_btn;
    private RadioButton radi_btn_one;
    String result = "";
    private SessionManager sessionManager;
    private TextView totla_price;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_choose_payment);
        this.totla_price = (TextView) findViewById(R.id.totla_price);
        this.card_cash_free = (CardView) findViewById(R.id.card_cash_free);
        this.card_cash = (CardView) findViewById(R.id.card_cash);
        this.radi_btn = (RadioButton) findViewById(R.id.radi_btn);
        this.radi_btn_one = (RadioButton) findViewById(R.id.radi_btn_one);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.sessionManager = new SessionManager((Activity) this);
        String str = Preference.get(this, Preference.key_Pakage_price);
        this.Pakage_price = str;
        this.totla_price.setText(str);
    }

    public void ChoosePaymentCheckout(View view) {
        if (this.PaymentType.equalsIgnoreCase("cash")) {
            Toast.makeText(this, "Success.", Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, PaymentCashFreeActivity.class));
        }
    }

    public void ChoosePaymentCash(View view) {
        this.radi_btn.setChecked(false);
        this.radi_btn_one.setChecked(true);
        this.PaymentType = "cash";
    }

    public void ChoosePaymentCashFree(View view) {
        this.radi_btn.setChecked(true);
        this.radi_btn_one.setChecked(false);
        this.PaymentType = "online";
    }

    public void choosePaymentBack(View view) {
        onBackPressed();
        finish();
    }
}
