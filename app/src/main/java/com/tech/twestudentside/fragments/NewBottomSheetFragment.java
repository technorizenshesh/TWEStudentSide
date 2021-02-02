package com.tech.twestudentside.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.LessonSummaryActivity;
import com.tech.twestudentside.activity.MyLessonPackageActivity;
import com.tech.twestudentside.activity.ReserveSpotActivity;
import com.tech.twestudentside.adapter.DaySelectDayAdapter;
import com.tech.twestudentside.adapter.DaySelectTimeAdapter;
import com.tech.twestudentside.model.DaysModel;

import java.util.ArrayList;
import java.util.Objects;

public class NewBottomSheetFragment extends BottomSheetDialogFragment {

    private static String s2;
    LinearLayout bokkingSit;
    TextView txt_time_select;
    RecyclerView recycler_time_slot;
    private ArrayList<DaysModel> modelList = new ArrayList<>();
    private DaySelectTimeAdapter mAdapter;

    public static NewBottomSheetFragment newInstance(String s1) {
        NewBottomSheetFragment fragment = new NewBottomSheetFragment();
        s2= s1;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_new_bottom_sheet, null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));

        txt_time_select= (TextView) contentView.findViewById(R.id.txt_time_select);
        bokkingSit= (LinearLayout) contentView.findViewById(R.id.bokkingSit);
        recycler_time_slot= (RecyclerView) contentView.findViewById(R.id.recycler_time_slot);
        bokkingSit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), MyLessonPackageActivity.class));
            }
        });

        if(s2.equalsIgnoreCase("1"))
        {
            txt_time_select.setText("Morning");
            addMorningSelect();

        }else if(s2.equalsIgnoreCase("2"))
        {
            txt_time_select.setText("Afternoon");
            addOfterNoonSelect();

        }else if(s2.equalsIgnoreCase("3"))
        {
            txt_time_select.setText("Evening");
            eveningTimeSelect();

        }

        setAdapterDay();
    }

    private void setAdapterDay() {

        mAdapter = new DaySelectTimeAdapter(getActivity(), modelList);
        recycler_time_slot.setHasFixedSize(true);
        // use a linear layout manager
        recycler_time_slot.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
     //   LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
       // recycler_time_slot.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recycler_time_slot.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new DaySelectTimeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, DaysModel model) {


            }
        });
    }

    private void addMorningSelect() {
        modelList.clear();
        modelList.add(new DaysModel("7-8"));
        modelList.add(new DaysModel("8-9"));
        modelList.add(new DaysModel("9-10"));
        modelList.add(new DaysModel("10-11"));
        modelList.add(new DaysModel("11-12"));
    }
    private void addOfterNoonSelect() {
        modelList.clear();
        modelList.add(new DaysModel("12-1"));
        modelList.add(new DaysModel("1-2"));
        modelList.add(new DaysModel("2-3"));
        modelList.add(new DaysModel("3-4"));
        modelList.add(new DaysModel("4-5"));
    }

    private void eveningTimeSelect() {
        modelList.clear();
        modelList.add(new DaysModel("6-7"));
        modelList.add(new DaysModel("7-8"));
        modelList.add(new DaysModel("8-9"));
        modelList.add(new DaysModel("9-10"));
        modelList.add(new DaysModel("10-11"));
    }
}