package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.TutorTrackngActivity;
import com.tech.twestudentside.listner.FragmentListener;


public class TodayClassDetailFragment extends Fragment {


    FragmentListener listener;

    public TodayClassDetailFragment(FragmentListener listener) {
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
        View view= inflater.inflate(R.layout.fragment_today_class_detail, container, false);

        CardView cardclassDeatils=view.findViewById(R.id.cardclassDeatils);

        cardclassDeatils.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TutorTrackngActivity.class));
            }
        });

        return view;
    }
}