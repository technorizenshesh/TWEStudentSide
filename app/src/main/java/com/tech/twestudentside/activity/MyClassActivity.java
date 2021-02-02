package com.tech.twestudentside.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.CurrentFragment;
import com.tech.twestudentside.fragments.PastFragment;
import com.tech.twestudentside.listner.FragmentListener;

public class MyClassActivity extends AppCompatActivity implements FragmentListener, View.OnClickListener {


    TextView currentTabId, pastTabId;
    FrameLayout frameLayoutHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_history);

        frameLayoutHistory = findViewById(R.id.frameLayout_HistoryId);

        loadFragment(new CurrentFragment(this));


        currentTabId = findViewById(R.id.current_TabId);

        pastTabId = findViewById(R.id.past_TabId);

        currentTabId.setOnClickListener(this);
        pastTabId.setOnClickListener(this);
    }

    public void backMyHistory(View view) {
        onBackPressed();
        finish();

    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout_HistoryId, fragment).commit();

    }


    @Override
    public void click(Fragment fragment) {
        loadFragment(fragment);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.current_TabId:

                currentTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
                pastTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                currentTabId.setTextColor(Color.parseColor("#ffffff"));
                pastTabId.setTextColor(Color.parseColor("#000000"));
                loadFragment(new CurrentFragment(this));


                break;


            case R.id.past_TabId:

                currentTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                pastTabId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
                currentTabId.setTextColor(Color.parseColor("#000000"));
                pastTabId.setTextColor(Color.parseColor("#ffffff"));
                loadFragment(new PastFragment(this));


                break;

        }
    }
}