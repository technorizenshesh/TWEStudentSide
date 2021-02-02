package com.tech.twestudentside.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.PaymentStatementActivity;
import com.tech.twestudentside.listner.FragmentListener;


public class TotalPaidFragment extends Fragment {


   FragmentListener listener;

    public TotalPaidFragment(FragmentListener listener) {
        this.listener=listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_total_paid, container, false);

        return view;
    }
}