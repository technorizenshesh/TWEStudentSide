package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.AdapterRecomended;
import com.tech.twestudentside.adapter.AdapterRelatedProperty;
import com.tech.twestudentside.adapter.SliderAdapterExample;
import com.tech.twestudentside.model.Get_detailsModel;
import com.tech.twestudentside.model.RelatedPropertyModel;
import com.tech.twestudentside.model.SliderItem;
import com.tech.twestudentside.model.home_model;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrandingActivity extends AppCompatActivity {

    SliderView sliderView;
    private SliderAdapterExample adapter;
    ImageView iv_back;

    ArrayList<RelatedPropertyModel> arrayList;
    RecyclerView recyclerView1;
    int[] icons = {R.drawable.penthouse,R.drawable.penthouse,R.drawable.penthouse,
            R.drawable.penthouse,R.drawable.penthouse,R.drawable.penthouse};
    String[] iconsName = {"Villas,Township,Penthouse,Compound,Office,Plots"};

    String TutorDetails_Id ="";
   private ProgressBar progressBar;
    private SessionManager sessionManager;

    TextView txt_Education;
    TextView txt_Experience;
    TextView txt_Language;
    TextView txt_Certification;
    TextView txt_Affilation;
    TextView txt_address;
    TextView txt_tutor_name;
    TextView txt_year_count;
    TextView distance;
    TextView txt_gender;
    TextView txt_payment_per_hr;

    RoundedImageView profile_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_tranding);

        iv_back = findViewById(R.id.iv_back);
        txt_Education = findViewById(R.id.txt_Education);
        txt_Experience = findViewById(R.id.txt_Experience);
        txt_Language = findViewById(R.id.txt_Language);
        txt_Certification = findViewById(R.id.txt_Certification);
        txt_Affilation = findViewById(R.id.txt_Affilation);
        profile_img = findViewById(R.id.profile_img);
        txt_tutor_name = findViewById(R.id.txt_tutor_name);
        txt_year_count = findViewById(R.id.txt_year_count);
        distance = findViewById(R.id.distance);
        txt_gender = findViewById(R.id.txt_gender);
        txt_payment_per_hr = findViewById(R.id.txt_payment_per_hr);
        txt_address = findViewById(R.id.txt_address);

        progressBar = findViewById(R.id.progressBar);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        sessionManager = new SessionManager(TrandingActivity.this);

        recyclerView1 = findViewById(R.id.recycler_viewTranding2);
        arrayList = new ArrayList<>();

        recyclerView1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,
                false));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());

        for (int i = 0; i < icons.length - 1; i++) {
            RelatedPropertyModel relatedPropertyModel = new RelatedPropertyModel();
            relatedPropertyModel.setImage(icons[i]);
            //itemModel.setName(iconsName[i]);

            //add in array list
            arrayList.add(relatedPropertyModel);
        }

        AdapterRelatedProperty adapterRelatedProperty = new AdapterRelatedProperty(this, arrayList);
        recyclerView1.setAdapter(adapterRelatedProperty);

        if (sessionManager.isNetworkAvailable()) {

            progressBar.setVisibility(View.VISIBLE);

            getHomeDetailsApi();

        }else {

            Toast.makeText(TrandingActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

    }

    public void MapOpenInit(View view) {
        startActivity(new Intent(TrandingActivity.this, MapActivity.class));
    }

    public void lessonInitTranding(View view) {
        startActivity(new Intent(TrandingActivity.this, ReserveSpotActivity.class));

    }

    public void call_Video(View view) {
        startActivity(new Intent(TrandingActivity.this, VideoCallingActivity.class));
    }

    public void attendance(View view) {
        startActivity(new Intent(TrandingActivity.this,AttendanceActivity.class));

    }

    public void PhotoGalleryInit(View view) {
        startActivity(new Intent(TrandingActivity.this,PortFoliyoActivity.class));
    }

    private void getHomeDetailsApi() {

        String  TutorDetails_Id = Preference.get(TrandingActivity.this,Preference.KEY_Tutor_id);

        Call<Get_detailsModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_student_details(TutorDetails_Id);

        call.enqueue(new Callback<Get_detailsModel>() {
            @Override
            public void onResponse(Call<Get_detailsModel> call, Response<Get_detailsModel> response) {

                try {

                    Get_detailsModel finallyPr = response.body();

                    progressBar.setVisibility(View.GONE);

                    String status   = finallyPr.getStatus ();
                    String message = finallyPr.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        String userName = finallyPr.getResult().getTutorDetails().getUsername();
                        String ProfileImage = finallyPr.getResult().getProfileImage();
                        String Gender = finallyPr.getResult().getGender();
                        String Lat = finallyPr.getResult().getTutorDetails().getLat();
                        String Long = finallyPr.getResult().getTutorDetails().getLon();
                        String Dob = finallyPr.getResult().getDob();
                        String PerHour_payment = finallyPr.getResult().getPerHourPayment();
                        String Educaation = finallyPr.getResult().getEducation();
                        String Language = finallyPr.getResult().getLanguage();
                        String Certification = finallyPr.getResult().getCertification();
                        String Affilation = finallyPr.getResult().getAffiliations();

                        txt_tutor_name.setText(userName);
                        txt_gender.setText(Gender);
                        txt_payment_per_hr.setText(PerHour_payment);
                        txt_Education.setText(Educaation);
                        txt_Language.setText(Language);
                        txt_Certification.setText(Certification);
                        txt_Affilation.setText(Affilation);
                      //  txt_address.setText(finallyPr.getResult().getAffiliations());

                        txt_year_count.setText(getAge(Dob)+" years");

                        Toast.makeText(TrandingActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(TrandingActivity.this, message, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Get_detailsModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }
}