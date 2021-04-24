package com.tech.twestudentside.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.ClassTodayAdapter;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.ClassTodayModal;
import java.util.ArrayList;
import java.util.List;

public class ScheduledFragment extends Fragment {
    FragmentListener listener;
    View view;

    public ScheduledFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_scheduled, container, false);
        init1();
        init();
        return this.view;
    }

    public void init1() {
        RecyclerView classToday_recyclerview = (RecyclerView) this.view.findViewById(R.id.classToday_recyclerview);
        classToday_recyclerview.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        List<ClassTodayModal> classTodayModalList = new ArrayList<>();
        classToday_recyclerview.setLayoutManager(layoutManager);
        classToday_recyclerview.setAdapter(new ClassTodayAdapter(getActivity(), classTodayModalList));
    }

    public void init() {
        RecyclerView upcomingClass_recyclerview = (RecyclerView) this.view.findViewById(R.id.upcomingClass_recyclerview);
        upcomingClass_recyclerview.setHasFixedSize(true);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        List<ClassTodayModal> classTodayModalList1 = new ArrayList<>();
        upcomingClass_recyclerview.setLayoutManager(layoutManager1);
        upcomingClass_recyclerview.setAdapter(new ClassTodayAdapter(getActivity(), classTodayModalList1));
    }
}
