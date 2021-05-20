package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.BookingHistoryRecyclerViewAdapter;
import com.tech.twestudentside.databinding.ActivityBookingHistoryStatusBinding;
import com.tech.twestudentside.model.BookingHistory;
import com.tech.twestudentside.model.BookingHistoryModel;
import com.tech.twestudentside.model.BookingHistoryStatusModel;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingHistoryStatus extends AppCompatActivity {

    ActivityBookingHistoryStatusBinding binding;
    BookingHistoryRecyclerViewAdapter mAdapter;


    private SessionManager sessionManager;
    private ArrayList<BookingHistoryModel> modelList = new ArrayList<>();

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

        sessionManager = new SessionManager(BookingHistoryStatus.this);

        if (sessionManager.isNetworkAvailable()) {
            binding.progressBar.setVisibility(View.VISIBLE);
            getBookingStatus();
        } else {
            Toast.makeText(BookingHistoryStatus.this, R.string.checkInternet, Toast.LENGTH_LONG).show();
        }

    }


    private void setAdapter(ArrayList<BookingHistoryModel> modelList) {

        mAdapter = new BookingHistoryRecyclerViewAdapter(this, modelList);
        binding.recyclerViewBookingHistory.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewBookingHistory.setLayoutManager(linearLayoutManager);
        binding.recyclerViewBookingHistory.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new BookingHistoryRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, BookingHistoryModel model) {

            }
        });
    }

    private void getBookingStatus() {

        String UserId =  Preference.get(BookingHistoryStatus.this, Preference.KEY_USER_ID);

        Call<BookingHistory> call = RetrofitClients
                .getInstance()
                .getApi()
                .booking_history(UserId);

        call.enqueue(new Callback<BookingHistory>() {
            @Override
            public void onResponse(Call<BookingHistory> call, Response<BookingHistory> response) {

                binding.progressBar.setVisibility(View.GONE);

                try {

                    BookingHistory myProductList=response.body();

                    String status= myProductList.getStatus().toString();
                    String Message= myProductList.getMessage().toString();

                    if (status.equalsIgnoreCase("1")) {
                        binding.txtEmptyTutor.setVisibility(View.GONE);

                        modelList= (ArrayList<BookingHistoryModel>) myProductList.getResult();

                        setAdapter(modelList);

                    } else {
                        binding.txtEmptyTutor.setVisibility(View.VISIBLE);
                        Toast.makeText(BookingHistoryStatus.this, Message, Toast.LENGTH_SHORT).show();
                        Toast.makeText(BookingHistoryStatus.this, Message, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    binding.txtEmptyTutor.setVisibility(View.VISIBLE);
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<BookingHistory> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                binding.txtEmptyTutor.setVisibility(View.VISIBLE);
                Toast.makeText(BookingHistoryStatus.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}