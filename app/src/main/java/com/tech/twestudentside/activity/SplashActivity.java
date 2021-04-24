package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.appevents.AppEventsConstants;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_TIME = 3000;
    String User_id;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView(R.layout.activity_splash);
        handlerMethod();
    }

    private void handlerMethod() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SplashActivity splashActivity = SplashActivity.this;
                splashActivity.User_id = Preference.get(splashActivity, Preference.KEY_USER_ID);
                if (SplashActivity.this.User_id == null || SplashActivity.this.User_id.trim().equalsIgnoreCase(AppEventsConstants.EVENT_PARAM_VALUE_NO) || SplashActivity.this.User_id.equalsIgnoreCase("")) {
                    SplashActivity.this.startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                    SplashActivity.this.finish();
                    return;
                }
                SplashActivity.this.startActivity(new Intent(SplashActivity.this, HomeActvity.class));
                SplashActivity.this.finish();
            }
        }, 3000);
    }
}
