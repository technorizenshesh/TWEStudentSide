package com.tech.twestudentside.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;

import com.tech.twestudentside.R;
import com.tech.twestudentside.listner.FragmentListener;

public class MapFragment extends Fragment {
    LinearLayout gridViewMapId;
    FragmentListener listener;
    LinearLayout mapViewMapId;

    public MapFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.grid_ViewMapId);
        this.gridViewMapId = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MapFragment.this.gridViewMapId.setBackgroundDrawable(MapFragment.this.getResources().getDrawable(R.drawable.color_pink));
                MapFragment.this.mapViewMapId.setBackgroundDrawable(MapFragment.this.getResources().getDrawable(R.drawable.color_gray));
                MapFragment.this.listener.click(new Want_TutorFragment(MapFragment.this.listener));
            }
        });
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.map_ViewMapId);
        this.mapViewMapId = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MapFragment.this.gridViewMapId.setBackgroundDrawable(MapFragment.this.getResources().getDrawable(R.drawable.color_gray));
                MapFragment.this.mapViewMapId.setBackgroundDrawable(MapFragment.this.getResources().getDrawable(R.drawable.color_pink));
                MapFragment.this.listener.click(new MapFragment(MapFragment.this.listener));
            }
        });
        return view;
    }
}
