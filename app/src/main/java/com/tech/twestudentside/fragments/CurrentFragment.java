package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.MyClassActivity;
import com.tech.twestudentside.activity.TrackingPageActivity;
import com.tech.twestudentside.listner.FragmentListener;


public class CurrentFragment extends Fragment {

    FragmentListener listener;
    TextView viewmore_id;

    public CurrentFragment(FragmentListener listener) {
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
        View view= inflater.inflate(R.layout.fragment_upcoming, container, false);

        viewmore_id= view.findViewById(R.id.viewmoreId);

        viewmore_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TrackingPageActivity.class));

            }
        });




        return view;
    }
}