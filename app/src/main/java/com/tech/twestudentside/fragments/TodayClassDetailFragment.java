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

    public TodayClassDetailFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_class_detail, container, false);
        ((CardView) view.findViewById(R.id.cardclassDeatils)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                TodayClassDetailFragment.this.startActivity(new Intent(TodayClassDetailFragment.this.getActivity(), TutorTrackngActivity.class));
            }
        });
        return view;
    }
}
