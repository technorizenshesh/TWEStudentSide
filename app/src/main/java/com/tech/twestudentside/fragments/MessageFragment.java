package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.ChatActivity;
import com.tech.twestudentside.listner.FragmentListener;


public class MessageFragment extends Fragment {

    FragmentListener listener;
    ImageView iv_back;
    CardView message_card1;

    public
    MessageFragment(FragmentListener listener) {
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
        View view= inflater.inflate(R.layout.fragment_message, container, false);


        message_card1=view.findViewById(R.id.message_card1);
        message_card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener.click(new HomeFragment(listener));
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });


        return  view;
    }
}