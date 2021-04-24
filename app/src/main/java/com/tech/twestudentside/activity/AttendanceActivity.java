package com.tech.twestudentside.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.DailyAttendanceFragment;
import com.tech.twestudentside.fragments.MonthlyAttendanceFragment;
import com.tech.twestudentside.fragments.WeeklyAttendanceFragment;
import com.tech.twestudentside.listner.FragmentListener;

public class AttendanceActivity extends AppCompatActivity implements FragmentListener, View.OnClickListener {
    TextView daily_TabtxtId;
    FrameLayout frameLayoutAttendance;
    TextView monthly_TabtxtId;
    TextView weekly_TabtxtId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_attendance);
        this.frameLayoutAttendance = (FrameLayout) findViewById(R.id.frameLayout_attendance);
        loadFragment(new DailyAttendanceFragment(this));
        this.daily_TabtxtId = (TextView) findViewById(R.id.daily_TabtxtId);
        this.weekly_TabtxtId = (TextView) findViewById(R.id.weekly_txtId);
        this.monthly_TabtxtId = (TextView) findViewById(R.id.monthly_txtId);
        this.daily_TabtxtId.setOnClickListener(this);
        this.weekly_TabtxtId.setOnClickListener(this);
        this.monthly_TabtxtId.setOnClickListener(this);
    }

    public void backAttendanceInit(View view) {
        onBackPressed();
        finish();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.daily_TabtxtId) {
            this.daily_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
            this.weekly_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.monthly_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.daily_TabtxtId.setTextColor(Color.parseColor("#05346d"));
            this.weekly_TabtxtId.setTextColor(Color.parseColor("#000000"));
            this.monthly_TabtxtId.setTextColor(Color.parseColor("#000000"));
            loadFragment(new DailyAttendanceFragment(this));
        } else if (id == R.id.monthly_txtId) {
            this.daily_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.weekly_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.monthly_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
            this.daily_TabtxtId.setTextColor(Color.parseColor("#000000"));
            this.weekly_TabtxtId.setTextColor(Color.parseColor("#000000"));
            this.monthly_TabtxtId.setTextColor(Color.parseColor("#05346d"));
            loadFragment(new MonthlyAttendanceFragment(this));
        } else if (id == R.id.weekly_txtId) {
            this.daily_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.weekly_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
            this.monthly_TabtxtId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.daily_TabtxtId.setTextColor(Color.parseColor("#000000"));
            this.weekly_TabtxtId.setTextColor(Color.parseColor("#05346d"));
            this.monthly_TabtxtId.setTextColor(Color.parseColor("#000000"));
            loadFragment(new WeeklyAttendanceFragment(this));
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_attendance, fragment).commit();
    }

    public void click(Fragment fragment) {
        loadFragment(fragment);
    }
}
