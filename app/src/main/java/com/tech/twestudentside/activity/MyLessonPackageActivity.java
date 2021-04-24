package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.AppEventsConstants;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.adapter.PriceAdapter;
import com.tech.twestudentside.model.GetCalculaterModel;
import com.tech.twestudentside.model.pricelistModel;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyLessonPackageActivity extends AppCompatActivity {
    LinearLayout LL_full;
    LinearLayout LL_hourly;
    LinearLayout LL_monthLy;
    LinearLayout LL_weeekly;
    String TotalPrice_Full_course = "";
    String TotalPrice_hourly = "";
    String TotalPrice_monthly = "";
    String TotalPrice_weekly = "";
    private PriceAdapter mAdapter;
    private ArrayList<pricelistModel> modelList = new ArrayList<>();
    ProgressBar progressBar;
    private RecyclerView recycler_bookdetails;
    /* access modifiers changed from: private */
    public SessionManager sessionManager;
    /* access modifiers changed from: private */
    public TextView txt1Tab;
    /* access modifiers changed from: private */
    public TextView txt3Tab;
    TextView txt_fullcourse_discount;
    TextView txt_fullcourse_price;
    TextView txt_hr_price;
    TextView txt_monthly_discount;
    TextView txt_monthly_price;
    TextView txt_total_price_Pay;
    TextView txt_total_time;
    TextView txt_total_time_full_course;
    TextView txt_total_time_monthly;
    TextView txt_total_time_weekly;
    TextView txt_weekly_discount;
    TextView txt_weekly_price;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lesson_package);
        this.txt_total_price_Pay = (TextView) findViewById(R.id.txt_total_price_Pay);
        this.txt_weekly_discount = (TextView) findViewById(R.id.txt_weekly_discount);
        this.txt_monthly_discount = (TextView) findViewById(R.id.txt_monthly_discount);
        this.txt_fullcourse_discount = (TextView) findViewById(R.id.txt_fullcourse_discount);
        this.sessionManager = new SessionManager((Activity) this);
        this.txt1Tab = (TextView) findViewById(R.id.txt1Tab);
        this.txt3Tab = (TextView) findViewById(R.id.txt3Tab);
        this.recycler_bookdetails = (RecyclerView) findViewById(R.id.recycler_bookdetails);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.txt_hr_price = (TextView) findViewById(R.id.txt_hr_price);
        this.txt_weekly_price = (TextView) findViewById(R.id.txt_weekly_price);
        this.txt_monthly_price = (TextView) findViewById(R.id.txt_monthly_price);
        this.txt_fullcourse_price = (TextView) findViewById(R.id.txt_fullcourse_price);
        this.txt_total_time = (TextView) findViewById(R.id.txt_total_time);
        this.txt_total_time_weekly = (TextView) findViewById(R.id.txt_total_time_weekly);
        this.txt_total_time_monthly = (TextView) findViewById(R.id.txt_total_time_monthly);
        this.txt_total_time_full_course = (TextView) findViewById(R.id.txt_total_time_full_course);
        String str = Preference.get(this, Preference.KEY_Tutor_price_indivisual);
        String str2 = Preference.get(this, Preference.KEY_Tutor_price_grp);
        this.txt1Tab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (MyLessonPackageActivity.this.sessionManager.isNetworkAvailable()) {
                    MyLessonPackageActivity.this.progressBar.setVisibility(View.VISIBLE);
                    MyLessonPackageActivity.this.get_tutor_package(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                } else {
                    Toast.makeText(MyLessonPackageActivity.this, R.string.checkInternet, Toast.LENGTH_LONG).show();
                }
                MyLessonPackageActivity.this.txt1Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorWhite));
                MyLessonPackageActivity.this.txt3Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorBlack));
                MyLessonPackageActivity.this.txt1Tab.setBackgroundResource(R.drawable.color_pink);
                MyLessonPackageActivity.this.txt3Tab.setBackgroundResource(R.drawable.color_gray);
            }
        });
        this.txt3Tab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (MyLessonPackageActivity.this.sessionManager.isNetworkAvailable()) {
                    MyLessonPackageActivity.this.progressBar.setVisibility(View.VISIBLE);
                    MyLessonPackageActivity.this.get_tutor_package("1");
                } else {
                    Toast.makeText(MyLessonPackageActivity.this, R.string.checkInternet, Toast.LENGTH_LONG).show();
                }
                MyLessonPackageActivity.this.txt1Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorBlack));
                MyLessonPackageActivity.this.txt3Tab.setTextColor(ContextCompat.getColor(MyLessonPackageActivity.this, R.color.colorWhite));
                MyLessonPackageActivity.this.txt1Tab.setBackgroundResource(R.drawable.color_gray);
                MyLessonPackageActivity.this.txt3Tab.setBackgroundResource(R.drawable.color_pink);
            }
        });
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            get_tutor_package(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        } else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        setAdapter();
    }

    public void backPressFromMyLesson(View view) {
        onBackPressed();
        finish();
    }

    public void continueLessonInit(View view) {
        Preference.save(this, Preference.key_Pakage, "hourly");
        Preference.save(this, Preference.key_Pakage_price, this.TotalPrice_hourly);
        startActivity(new Intent(this, LessonSummaryActivity.class));
    }

    public void continueLessonInit_weekly(View view) {
        Preference.save(this, Preference.key_Pakage, "Weekly");
        Preference.save(this, Preference.key_Pakage_price, this.TotalPrice_weekly);
        startActivity(new Intent(this, LessonSummaryActivity.class));
    }

    public void continueLessonInit_monthly(View view) {
        Preference.save(this, Preference.key_Pakage, "Monthly");
        Preference.save(this, Preference.key_Pakage_price, this.TotalPrice_monthly);
        startActivity(new Intent(this, LessonSummaryActivity.class));
    }

    public void continueLessonInit_full(View view) {
        Preference.save(this, Preference.key_Pakage, "Full Course");
        Preference.save(this, Preference.key_Pakage_price, this.TotalPrice_Full_course);
        startActivity(new Intent(this, LessonSummaryActivity.class));
    }

    private void setAdapter() {
        this.modelList.clear();
        this.modelList.add(new pricelistModel("Hourly", "00%"));
        this.modelList.add(new pricelistModel("Weekly", "05%"));
        this.modelList.add(new pricelistModel("Monthly", "10%"));
        this.modelList.add(new pricelistModel("Full Course", "20%"));
        this.mAdapter = new PriceAdapter(this, this.modelList);
        this.recycler_bookdetails.setHasFixedSize(true);
        this.recycler_bookdetails.setLayoutManager(new LinearLayoutManager(this));
        this.recycler_bookdetails.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new PriceAdapter.OnItemClickListener() {
            public void onItemClick(View view, int position, pricelistModel model) {
            }
        });
    }

    /* access modifiers changed from: private */
    public void get_tutor_package(String type) {
        RetrofitClients.getInstance().getApi().get_tutor_package(Preference.get(this, Preference.KEY_Tutor_id), type).enqueue(new Callback<GetCalculaterModel>() {
            public void onResponse(Call<GetCalculaterModel> call, Response<GetCalculaterModel> response) {
                MyLessonPackageActivity.this.progressBar.setVisibility(View.GONE);
                try {
                    GetCalculaterModel myTimeSlot = response.body();
                    String status = myTimeSlot.getStatus();
                    String message = myTimeSlot.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        MyLessonPackageActivity myLessonPackageActivity = MyLessonPackageActivity.this;
                        myLessonPackageActivity.TotalPrice_weekly = myTimeSlot.getResult().getWeeklyDetails().getTotalPrice() + "";
                        MyLessonPackageActivity myLessonPackageActivity2 = MyLessonPackageActivity.this;
                        myLessonPackageActivity2.TotalPrice_monthly = myTimeSlot.getResult().getMonthlyDetails().getTotalPrice() + "";
                        MyLessonPackageActivity myLessonPackageActivity3 = MyLessonPackageActivity.this;
                        myLessonPackageActivity3.TotalPrice_Full_course = myTimeSlot.getResult().getFullCourse().getTotalPrice() + "";
                        TextView textView = MyLessonPackageActivity.this.txt_weekly_discount;
                        textView.setText(myTimeSlot.getResult().getWeeklyDetails().getTotalPrice() + "");
                        TextView textView2 = MyLessonPackageActivity.this.txt_monthly_discount;
                        textView2.setText(myTimeSlot.getResult().getMonthlyDetails().getTotalPrice() + "");
                        TextView textView3 = MyLessonPackageActivity.this.txt_fullcourse_discount;
                        textView3.setText(myTimeSlot.getResult().getFullCourse().getTotalPrice() + "");
                        TextView textView4 = MyLessonPackageActivity.this.txt_hr_price;
                        textView4.setText("Actual Price Rs." + myTimeSlot.getResult().getHourlyDetails().getStudentPrice());
                        TextView textView5 = MyLessonPackageActivity.this.txt_weekly_price;
                        textView5.setText("Actual Price Rs." + myTimeSlot.getResult().getWeeklyDetails().getStudentPrice());
                        TextView textView6 = MyLessonPackageActivity.this.txt_monthly_price;
                        textView6.setText("Actual Price Rs." + myTimeSlot.getResult().getMonthlyDetails().getStudentPrice());
                        TextView textView7 = MyLessonPackageActivity.this.txt_fullcourse_price;
                        textView7.setText("Actual Price Rs." + myTimeSlot.getResult().getFullCourse().getStudentPrice());
                        TextView textView8 = MyLessonPackageActivity.this.txt_total_time;
                        textView8.setText(myTimeSlot.getResult().getFullCourseTime() + "/hr");
                        TextView textView9 = MyLessonPackageActivity.this.txt_total_time_weekly;
                        textView9.setText(myTimeSlot.getResult().getFullCourseTime() + "/hr");
                        TextView textView10 = MyLessonPackageActivity.this.txt_total_time_monthly;
                        textView10.setText(myTimeSlot.getResult().getFullCourseTime() + "/hr");
                        TextView textView11 = MyLessonPackageActivity.this.txt_total_time_full_course;
                        textView11.setText(myTimeSlot.getResult().getFullCourseTime() + "/hr");
                        MyLessonPackageActivity myLessonPackageActivity4 = MyLessonPackageActivity.this;
                        myLessonPackageActivity4.TotalPrice_hourly = "" + myTimeSlot.getResult().getHourlyDetails().getTotalPrice();
                        TextView textView12 = MyLessonPackageActivity.this.txt_total_price_Pay;
                        textView12.setText("" + myTimeSlot.getResult().getHourlyDetails().getTotalPrice());
                        return;
                    }
                    Toast.makeText(MyLessonPackageActivity.this, message, Toast.LENGTH_LONG).show();
                    MyLessonPackageActivity.this.progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    Toast.makeText(MyLessonPackageActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<GetCalculaterModel> call, Throwable t) {
                MyLessonPackageActivity.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(MyLessonPackageActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
