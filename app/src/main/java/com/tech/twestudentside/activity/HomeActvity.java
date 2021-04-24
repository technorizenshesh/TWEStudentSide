package com.tech.twestudentside.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.AccountFragment;
import com.tech.twestudentside.fragments.FavouriteFragment;
import com.tech.twestudentside.fragments.HomeFragment;
import com.tech.twestudentside.fragments.MessageFragment;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.utils.SessionManager;

public class HomeActvity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, FragmentListener {
    private BottomNavigationView navigationView;
    private SessionManager sessionManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_home_actvity);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        this.navigationView = bottomNavigationView;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        loadFragment(new HomeFragment(this));
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.account /*2131361875*/:
                setColorOnBar(R.color.colorBackground);
                loadFragment(new AccountFragment(this));
                return true;
            case R.id.chat /*2131361976*/:
                setColorOnBar(R.color.colorBackground);
                loadFragment(new MessageFragment(this));
                return true;
            case R.id.favourite /*2131362100*/:
                setColorOnBar(R.color.colorWhite);
                loadFragment(new FavouriteFragment(this));
                return true;
            case R.id.home /*2131362139*/:
                setColorOnBar(R.color.colorBackground);
                loadFragment(new HomeFragment(this));
                return true;
            default:
                return true;
        }
    }

    public void click(Fragment fragment) {
        loadFragment(fragment);
    }

    /* access modifiers changed from: package-private */
    public void setColorOnBar(int color) {
        if (Build.VERSION.SDK_INT >= 23) {
            getWindow().setStatusBarColor(getResources().getColor(color, getTheme()));
        } else if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(getResources().getColor(color));
        }
    }
}
