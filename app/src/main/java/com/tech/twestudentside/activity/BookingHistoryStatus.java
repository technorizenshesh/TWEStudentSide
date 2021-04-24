package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.BookingHistoryRecyclerViewAdapter;
import com.tech.twestudentside.databinding.ActivityBookingHistoryStatusBinding;
import com.tech.twestudentside.model.BookingHistoryStatusModel;

import java.util.ArrayList;

public class BookingHistoryStatus extends AppCompatActivity {

    ActivityBookingHistoryStatusBinding binding;
    BookingHistoryRecyclerViewAdapter mAdapter;

    RelativeLayout c;

    private ArrayList<BookingHistoryStatusModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_booking_history_status);

        binding.RRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setAdapter();

    }


    private void setAdapter() {

        modelList.add(new BookingHistoryStatusModel(""));
        modelList.add(new BookingHistoryStatusModel(""));
        modelList.add(new BookingHistoryStatusModel(""));

        mAdapter = new BookingHistoryRecyclerViewAdapter(this, modelList);
        binding.recyclerViewBookingHistory.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewBookingHistory.setLayoutManager(linearLayoutManager);
        binding.recyclerViewBookingHistory.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new BookingHistoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, BookingHistoryStatusModel model) {

            }
        });
    }

}