package com.tech.twestudentside.activity;

import android.content.Context;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.CategoryFragment1;
import com.tech.twestudentside.listner.FragmentListener;

public class CategorySelectActivity extends AppCompatActivity implements FragmentListener {
    Context mContext;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_category_select);
        this.mContext = this;
        loadFragment(new CategoryFragment1(this));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).commit();
    }

    public void click(Fragment fragment) {
        loadFragment(fragment);
    }
}
