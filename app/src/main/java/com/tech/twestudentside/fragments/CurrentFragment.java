package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.TrackingPageActivity;
import com.tech.twestudentside.listner.FragmentListener;

public class CurrentFragment extends Fragment {
    FragmentListener listener;
    TextView viewmore_id;

    public CurrentFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        TextView textView = (TextView) view.findViewById(R.id.viewmoreId);
        this.viewmore_id = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CurrentFragment.this.startActivity(new Intent(CurrentFragment.this.getActivity(), TrackingPageActivity.class));
            }
        });
        return view;
    }
}
