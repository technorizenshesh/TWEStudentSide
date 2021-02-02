package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_TIME = 3000;
    String User_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);}
        setContentView(R.layout.activity_splash);

        handlerMethod();
    }


    private void handlerMethod() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                User_id= Preference.get(SplashActivity.this,Preference.KEY_USER_ID);


                if(User_id != null && !User_id.trim().equalsIgnoreCase("0") && !User_id.equalsIgnoreCase("")){

                    Intent intent=new Intent(SplashActivity.this, HomeActvity.class);
                    startActivity(intent);
                    finish();

                }else
                {
                    Intent intent=new Intent(SplashActivity.this, WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }

           /*     startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                finish();*/
            }
        }, SPLASH_TIME);
    }

}