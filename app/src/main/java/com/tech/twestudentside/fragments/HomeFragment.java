package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.FillterActivity;
import com.tech.twestudentside.activity.SearchTutorActivity;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.listner.MyClickListner;

public class HomeFragment extends Fragment implements FragmentListener, View.OnClickListener, MyClickListner {
    String LocationId = "";
    ImageView et_RightFilter;
    TextView et_tophomeSpinner;
    FrameLayout frameLayout1;
    FragmentListener listener;
    ImageView notification_home;
    RelativeLayout relative_searchBtn;
    TextView search_home;
    TextView txt1home_Tab;
    TextView txt2home_Tab;
    TextView txt3home_Tab;

    public HomeFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.frameLayout1 = (FrameLayout) view.findViewById(R.id.frameLayout1);
        Preference.save(getActivity(), Preference.key_Type, "Online");
        loadFragment(new Want_TutorFragment(this));
        TextView textView = (TextView) view.findViewById(R.id.et_tophomeSpinner);
        this.et_tophomeSpinner = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new BottomSheetFragment(new MyClickListner() {
                    public final void clickListen(String str) {
                        HomeFragment.this.clickListen(str);
                    }
                }).show(HomeFragment.this.getChildFragmentManager(), "ModalBottomSheet");
            }
        });
        ImageView imageView = (ImageView) view.findViewById(R.id.notification_home);
        this.notification_home = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragment.this.listener.click(new NotificationFragment(HomeFragment.this.listener));
            }
        });
        ImageView imageView2 = (ImageView) view.findViewById(R.id.et_RightFilter);
        this.et_RightFilter = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.getActivity(), FillterActivity.class));
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.relative_searchBtn);
        this.relative_searchBtn = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.getActivity(), SearchTutorActivity.class));
            }
        });
        TextView textView2 = (TextView) view.findViewById(R.id.search_home);
        this.search_home = textView2;
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeFragment.this.startActivity(new Intent(HomeFragment.this.getActivity(), SearchTutorActivity.class));
            }
        });
        this.txt1home_Tab = (TextView) view.findViewById(R.id.txt1_homeTab);
        this.txt2home_Tab = (TextView) view.findViewById(R.id.txt2_homeTab);
        this.txt3home_Tab = (TextView) view.findViewById(R.id.txt3_homeTab);
        this.txt1home_Tab.setOnClickListener(this);
        this.txt2home_Tab.setOnClickListener(this);
        this.txt3home_Tab.setOnClickListener(this);
        return view;
    }

    private void loadFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.frameLayout1, fragment).commit();
    }

    public void click(Fragment fragment) {
        loadFragment(fragment);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt1_homeTab:
                Preference.save(getActivity(), Preference.key_Type, "Online");
                this.txt1home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
                this.txt2home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                this.txt3home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                loadFragment(new Want_TutorFragment(this));
                return;
            case R.id.txt2_homeTab:
                Preference.save(getActivity(), Preference.key_Type, "Tutor_Home");
                this.txt1home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                this.txt2home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
                this.txt3home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                loadFragment(new Want_TutorFragment(this));
                return;
            case R.id.txt3_homeTab:
                Preference.save(getActivity(), Preference.key_Type, "Student_Home");
                this.txt1home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                this.txt2home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                this.txt3home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
                loadFragment(new Want_TutorFragment(this));
                return;
            default:
                return;
        }
    }

    public void clickListen(String listenId) {
        this.LocationId = Preference.get(getActivity(), Preference.KEY_location_id);
        this.et_tophomeSpinner.setText(Preference.get(getActivity(), Preference.KEY_location_name));
    }
}
