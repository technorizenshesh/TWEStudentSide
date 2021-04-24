package com.tech.twestudentside.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;
import com.tech.twestudentside.R;
import com.tech.twestudentside.listner.FragmentListener;

public class NotificationFragment extends Fragment {
    ImageView iv_back;
    FragmentListener listener;

    public NotificationFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NotificationFragment.this.listener.click(new HomeFragment(NotificationFragment.this.listener));
            }
        });
        return view;
    }
}
