package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.CategoryFragment1;
import com.tech.twestudentside.listner.FragmentListener;

public class CategorySelectActivity extends AppCompatActivity implements FragmentListener {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_select);
        mContext=CategorySelectActivity.this;


        loadFragment(new CategoryFragment1(this));


    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }



    @Override
    public void click(Fragment fragment) {
        loadFragment(fragment);
    }
}
