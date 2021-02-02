package com.tech.twestudentside.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.FillterActivity;
import com.tech.twestudentside.activity.SearchTutorActivity;
import com.tech.twestudentside.activity.YourPackLessonActivity;
import com.tech.twestudentside.listner.FragmentListener;

import java.util.Objects;


public class HomeFragment extends Fragment  implements FragmentListener, View.OnClickListener {

    Context mContext;
    TextView txt1home_Tab,txt3home_Tab,txt2home_Tab, search_home;

    ImageView et_RightFilter, notification_home;
    TextView et_tophomeSpinner;

    RelativeLayout relative_searchBtn;

    FrameLayout frameLayout1;

    FragmentListener listener;


    public HomeFragment(FragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);


        frameLayout1 = view.findViewById(R.id.frameLayout1);

        Preference.save(getActivity(),Preference.key_Type,"Online");
        loadFragment(new Want_TutorFragment(this));


        et_tophomeSpinner=view.findViewById(R.id.et_tophomeSpinner);
        et_tophomeSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              BottomSheetFragment bottomSheetFragment= new BottomSheetFragment();
                bottomSheetFragment.show(getChildFragmentManager(),"ModalBottomSheet");
            }
        });


        notification_home=view.findViewById(R.id.notification_home);
        notification_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.click(new NotificationFragment(listener));
            }
        });

        et_RightFilter=view.findViewById(R.id.et_RightFilter);
        et_RightFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FillterActivity.class));
            }
        });

        relative_searchBtn=view.findViewById(R.id.relative_searchBtn);

        relative_searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), SearchTutorActivity.class));
            }
        });

        search_home=view.findViewById(R.id.search_home);
        search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchTutorActivity.class));
            }
        });


        txt1home_Tab = view.findViewById(R.id.txt1_homeTab);
        // txt2Tab = view.findViewById(R.id.txt2Tab);
        txt2home_Tab = view.findViewById(R.id.txt2_homeTab);

        txt3home_Tab = view.findViewById(R.id.txt3_homeTab);

        txt1home_Tab.setOnClickListener(this);
        // txt2Tab.setOnClickListener(this);
        txt2home_Tab.setOnClickListener(this);
        txt3home_Tab.setOnClickListener(this);



        return view;

    }

    private void loadFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.frameLayout1, fragment).commit();

    }



    @Override
    public void click(Fragment fragment) {
        loadFragment(fragment);

    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.txt1_homeTab:
                Preference.save(getActivity(),Preference.key_Type,"Online");
                txt1home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
                txt2home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                txt3home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                loadFragment(new Want_TutorFragment(this));


                break;

            case R.id.txt2_homeTab:
                Preference.save(getActivity(),Preference.key_Type,"Tutor_Home");
                txt1home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                txt2home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
                txt3home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
               // loadFragment(new Want_TutorFragment(this));
                loadFragment(new Want_TutorFragment(this));
              //  startActivity(new Intent(getActivity(), YourPackLessonActivity.class));
                break;

            case R.id.txt3_homeTab:
                Preference.save(getActivity(),Preference.key_Type,"Student_Home");
                txt1home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                txt2home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                txt3home_Tab.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_yellowbg));
                loadFragment(new Want_TutorFragment(this));

                // startActivity(new Intent(getActivity(), PhotosActivity.class));
                break;
        }

    }
}