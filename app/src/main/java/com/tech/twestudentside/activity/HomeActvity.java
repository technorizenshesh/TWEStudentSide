package com.tech.twestudentside.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.FavouriteFragment;
import com.tech.twestudentside.fragments.HomeFragment;
import com.tech.twestudentside.fragments.MessageFragment;
import com.tech.twestudentside.fragments.AccountFragment;
import com.tech.twestudentside.listner.FragmentListener;

public class HomeActvity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener, FragmentListener {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_home_actvity);


        navigationView = findViewById(R.id.bottom_navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        loadFragment(new HomeFragment(this));
    }


    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.home:
                setColorOnBar(R.color.colorBackground);
                loadFragment(new HomeFragment(this));
                break;
            case R.id.favourite:
                setColorOnBar(R.color.colorWhite);
                loadFragment(new FavouriteFragment(this));
                //startActivity(new Intent(HomeActivity.this,Tranding2Activity.class));
                break;

            case R.id.chat:
                setColorOnBar(R.color.colorBackground);
               loadFragment(new MessageFragment(this));
                break;

            case R.id.account:
                setColorOnBar(R.color.colorBackground);
                loadFragment(new AccountFragment(this));
                break;
        }

        return true;

    }


    @Override
    public void click(Fragment fragment) {
        loadFragment(fragment);
    }


    void setColorOnBar(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(color, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(color));
        }
    }


}