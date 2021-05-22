package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;

public class Activity_otp extends AppCompatActivity {

    String PhoneNumber = "7020764458";
    TextView txt_mobile;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView((int) R.layout.layout_otp);
        this.txt_mobile = (TextView) findViewById(R.id.txt_mobile);
        this.PhoneNumber = Preference.get(this, Preference.KEY_Mobile_number);
        this.txt_mobile.setText("We have sent you an SMS on 92+ 7020764458\n with 6 digit verfication code");
        TextView textView = this.txt_mobile;
        textView.setText("We have sent you an SMS on 92+ " + this.PhoneNumber + "\n with 6 digit verfication code");
    }

    public void OTPInit(View view) {
        startActivity(new Intent(this, CategorySelectActivity.class));
        finish();
    }

    public void backCancelBtn(View view) {
        onBackPressed();
        finish();
    }
}
