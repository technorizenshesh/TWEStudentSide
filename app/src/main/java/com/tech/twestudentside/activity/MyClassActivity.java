package com.tech.twestudentside.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.smarteist.autoimageslider.IndicatorView.animation.type.ColorAnimation;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.CurrentFragment;
import com.tech.twestudentside.fragments.PastFragment;
import com.tech.twestudentside.listner.FragmentListener;

public class MyClassActivity extends AppCompatActivity implements FragmentListener, View.OnClickListener {
    TextView currentTabId;
    FrameLayout frameLayoutHistory;
    TextView pastTabId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_my_history);
        this.frameLayoutHistory = (FrameLayout) findViewById(R.id.frameLayout_HistoryId);
        loadFragment(new CurrentFragment(this));
        this.currentTabId = (TextView) findViewById(R.id.current_TabId);
        this.pastTabId = (TextView) findViewById(R.id.past_TabId);
        this.currentTabId.setOnClickListener(this);
        this.pastTabId.setOnClickListener(this);
    }

    public void backMyHistory(View view) {
        onBackPressed();
        finish();
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_HistoryId, fragment).commit();
    }

    public void click(Fragment fragment) {
        loadFragment(fragment);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.current_TabId) {
            this.currentTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
            this.pastTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.currentTabId.setTextColor(Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR));
            this.pastTabId.setTextColor(Color.parseColor("#000000"));
            loadFragment(new CurrentFragment(this));
        } else if (id == R.id.past_TabId) {
            this.currentTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
            this.pastTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
            this.currentTabId.setTextColor(Color.parseColor("#000000"));
            this.pastTabId.setTextColor(Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR));
            loadFragment(new PastFragment(this));
        }
    }
}
