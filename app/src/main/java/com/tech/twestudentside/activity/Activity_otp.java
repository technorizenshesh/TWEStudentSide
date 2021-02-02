package com.tech.twestudentside.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;


public class Activity_otp extends AppCompatActivity {

    String PhoneNumber="7020764458";
    TextView txt_mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);}
        setContentView(R.layout.layout_otp);

        txt_mobile=findViewById(R.id.txt_mobile);
        PhoneNumber = Preference.get(Activity_otp.this,Preference.KEY_Mobile_number);
        txt_mobile.setText("We have sent you an SMS on 92+ 7020764458\n with 6 digit verfication code");
        txt_mobile.setText("We have sent you an SMS on 92+ "+PhoneNumber+"\n with 6 digit verfication code");


    }


    public void OTPInit(View view) {
        startActivity(new Intent(Activity_otp.this, CategorySelectActivity.class));
        finish();
    }

    public void backCancelBtn(View view) {
        onBackPressed();
        finish();
    }
}
