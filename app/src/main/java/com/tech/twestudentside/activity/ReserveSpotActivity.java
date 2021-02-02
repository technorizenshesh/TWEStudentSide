package com.tech.twestudentside.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.DaySelectDayAdapter;
import com.tech.twestudentside.fragments.BottomSheetFragment;
import com.tech.twestudentside.fragments.NewBottomSheetFragment;
import com.tech.twestudentside.model.DaysModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

public class ReserveSpotActivity extends AppCompatActivity {

    private TextView txt_morning_shift;
    private TextView txt_time_zone;
    private TextView txt_afternoon_shift;
    private TextView txt_evening_shift;
    private RecyclerView recycler_day_select;
    private LinearLayout RR_date;
    private TextView txt_dob;
    private TextView txt1Tab;
    private TextView txt3Tab;

    private ArrayList<DaysModel> modelList = new ArrayList<>();
    private DaySelectDayAdapter mAdapter;
    private int mYear, mMonth,mDay;
    String time_slot="2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_spot);

        TimeZone tz = TimeZone.getDefault();
        String TimeZone1= tz.getDisplayName(false, TimeZone.SHORT);

        txt_morning_shift=findViewById(R.id.txt_morning_shift);
        txt_time_zone=findViewById(R.id.txt_time_zone);
        txt_afternoon_shift=findViewById(R.id.txt_afternoon_shift);
        txt_evening_shift=findViewById(R.id.txt_evening_shift);
        RR_date=findViewById(R.id.RR_date);
        recycler_day_select=findViewById(R.id.recycler_day_select);
        txt_dob=findViewById(R.id.txt_dob);
        txt1Tab=findViewById(R.id.txt1Tab);
        txt3Tab=findViewById(R.id.txt3Tab);

        txt_time_zone.setText("( "+TimeZone1+" ) "+tz.getDisplayName());

        RR_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ReserveSpotActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                view.setVisibility(View.VISIBLE);
                              String dob = (dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
                              String dobNew = (year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                              //  txt_dob.setText(dob);
                               String newDate = getDate(dobNew);
                                txt_dob.setText(newDate);
                            //    String age = getAge(year,(monthOfYear+1),dayOfMonth);
                                //txt_age.setText(age+" Year");

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();


            }
        });


        txt_morning_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time_slot ="1";

                txt_morning_shift.setBackgroundResource(R.drawable.round_shapebg);
                txt_afternoon_shift.setBackgroundResource(R.drawable.round_shapebg_unselected);
                txt_evening_shift.setBackgroundResource(R.drawable.round_shapebg_unselected);

                txt_morning_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorWhite));
                txt_afternoon_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
                txt_evening_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));

            }
        });

        txt_afternoon_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time_slot ="2";

                txt_morning_shift.setBackgroundResource(R.drawable.round_shapebg_unselected);
                txt_afternoon_shift.setBackgroundResource(R.drawable.round_shapebg);
                txt_evening_shift.setBackgroundResource(R.drawable.round_shapebg_unselected);

                txt_morning_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
                txt_afternoon_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorWhite));
                txt_evening_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
            }
        });

        txt_evening_shift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                time_slot ="3";

                txt_morning_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
                txt_afternoon_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
                txt_evening_shift.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorWhite));

                txt_morning_shift.setBackgroundResource(R.drawable.round_shapebg_unselected);
                txt_afternoon_shift.setBackgroundResource(R.drawable.round_shapebg_unselected);
                txt_evening_shift.setBackgroundResource(R.drawable.round_shapebg);
            }
        });

        txt1Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorWhite));
                txt3Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));

                txt1Tab.setBackgroundResource(R.drawable.color_pink);
                txt3Tab.setBackgroundResource(R.drawable.color_gray);
            }
        });

        txt3Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
                txt3Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorWhite));

                txt1Tab.setBackgroundResource(R.drawable.color_gray);
                txt3Tab.setBackgroundResource(R.drawable.color_pink);
            }
        });

        setAdapterDay();
    }

    public void backPressReserveSpot(View view) {
        onBackPressed();
        finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ReserveBtnInit(View view) {

        NewBottomSheetFragment bottomSheetDialog = NewBottomSheetFragment.newInstance(time_slot);
        bottomSheetDialog.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");
        // startActivity(new Intent(ReserveSpotActivity.this, PurchasePlanActivity.class));
    }

    private void setAdapterDay() {
        modelList.clear();
        modelList.add(new DaysModel("Sunday"));
        modelList.add(new DaysModel("Monday"));
        modelList.add(new DaysModel("Tuesday"));
        modelList.add(new DaysModel("Wednesday"));
        modelList.add(new DaysModel("Thursday"));
        modelList.add(new DaysModel("Friday"));
        modelList.add(new DaysModel("Saturday"));

        mAdapter = new DaySelectDayAdapter(ReserveSpotActivity.this, modelList);
        recycler_day_select.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ReserveSpotActivity.this);
        recycler_day_select.setLayoutManager(new GridLayoutManager(ReserveSpotActivity.this, 3));
        recycler_day_select.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new DaySelectDayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, DaysModel model) {


            }
        });
    }

    private String getDate(String Date) {

        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dateObj = null;
        try {
            dateObj = curFormater.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat postFormater = new SimpleDateFormat("dd MMMM  yyyy");
        String newDateStr = postFormater.format(dateObj);
        return newDateStr;
    }

}