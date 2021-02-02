package com.tech.twestudentside.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tech.twestudentside.R;
import com.tech.twestudentside.listner.FragmentListener;

public class MapFragment extends Fragment {

    FragmentListener listener;
    LinearLayout gridViewMapId,mapViewMapId;


    public MapFragment(FragmentListener listener) {
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
        View view= inflater.inflate(R.layout.fragment_map, container, false);



        gridViewMapId= view.findViewById(R.id.grid_ViewMapId);
        gridViewMapId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gridViewMapId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
                mapViewMapId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                listener.click(new Want_TutorFragment(listener));
            }
        });

        mapViewMapId= view.findViewById(R.id.map_ViewMapId);
        mapViewMapId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                gridViewMapId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                mapViewMapId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
                listener.click(new MapFragment(listener));
            }
        });

        return  view;
    }
}