package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.DaySelectDayAdapter;
import com.tech.twestudentside.adapter.PriceAdapter;
import com.tech.twestudentside.model.pricelistModel;

import java.util.ArrayList;

public class MyLessonPackageActivity extends AppCompatActivity {

    private TextView txt1Tab;
    private TextView txt3Tab;
    private RecyclerView recycler_bookdetails;

    private ArrayList<pricelistModel> modelList = new ArrayList<>();
    private PriceAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lesson_package);

        txt1Tab=findViewById(R.id.txt1Tab);
        txt3Tab=findViewById(R.id.txt3Tab);
        recycler_bookdetails=findViewById(R.id.recycler_bookdetails);

        String PerHour = Preference.get(MyLessonPackageActivity.this,Preference.KEY_Tutor_price_indivisual);
        String PerHour_grp =   Preference.get(MyLessonPackageActivity.this,Preference.KEY_Tutor_price_grp);



        txt1Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorWhite));
                txt3Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorBlack));

                txt1Tab.setBackgroundResource(R.drawable.color_pink);
                txt3Tab.setBackgroundResource(R.drawable.color_gray);

            }
        });

        txt3Tab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                txt1Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorBlack));
                txt3Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorWhite));

                txt1Tab.setBackgroundResource(R.drawable.color_gray);
                txt3Tab.setBackgroundResource(R.drawable.color_pink);
            }
        });
        setAdapter();
    }

    public void backPressFromMyLesson(View view) {
        onBackPressed();
        finish();
    }

    public void continueLessonInit(View view) {
        startActivity(new Intent(MyLessonPackageActivity.this,LessonSummaryActivity.class));
    }

    private void setAdapter() {
        modelList.clear();
        modelList.add(new pricelistModel("Hourly","00%"));
        modelList.add(new pricelistModel("Weekly","05%"));
        modelList.add(new pricelistModel("Monthly","10%"));
        modelList.add(new pricelistModel("Full Course","20%"));

        mAdapter = new PriceAdapter(MyLessonPackageActivity.this, modelList);
        recycler_bookdetails.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyLessonPackageActivity.this);
        recycler_bookdetails.setLayoutManager(linearLayoutManager);
        recycler_bookdetails.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new PriceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, pricelistModel model) {


            }
        });
    }
}