package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TakeClassesActivity extends AppCompatActivity {


    private LinearLayout LL_student_home;
    private LinearLayout LL_tutor_home;
    private LinearLayout LL_online;

    private TextView txt_student_home;
    private TextView txt_tutor_home;
    private TextView txt_online;

    String where_to_teach="";

    private SessionManager sessionManager;
    private String android_id;
    private ProgressBar progressBar;
    private LinearLayout LL_submit;
    String result="";

    private String Dob="";
    private  String Gender="";
    private String about="";
    private String tutorFor= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);}
        setContentView(R.layout.activity_take_classes);

        LL_student_home=findViewById(R.id.LL_student_home);
        LL_tutor_home=findViewById(R.id.LL_tutor_home);
        LL_online=findViewById(R.id.LL_online);
        progressBar=findViewById(R.id.progressBar);
        LL_submit=findViewById(R.id.LL_submit);
        sessionManager = new SessionManager(TakeClassesActivity.this);

        txt_student_home=findViewById(R.id.txt_student_home);
        txt_tutor_home=findViewById(R.id.txt_tutor_home);
        txt_online=findViewById(R.id.txt_online);

        Intent intent= getIntent();
          if(intent !=null)
          {
              Dob = intent.getStringExtra("DOB").toString();
              Gender = intent.getStringExtra("Gender").toString();
              about = intent.getStringExtra("about").toString();
              tutorFor = intent.getStringExtra("tutorFor").toString();
          }

        LL_student_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                where_to_teach ="Student_Home";

                LL_student_home.setBackgroundResource(R.drawable.edit_bgbutton);
                LL_tutor_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_online.setBackgroundResource(R.drawable.edit_bgbutton_white);

                txt_student_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.colorWhite));
                txt_tutor_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                txt_online.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));


            }
        });

        LL_tutor_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                where_to_teach ="Tutor_Home";

                LL_student_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_tutor_home.setBackgroundResource(R.drawable.edit_bgbutton);
                LL_online.setBackgroundResource(R.drawable.edit_bgbutton_white);

                txt_student_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                txt_tutor_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.colorWhite));
                txt_online.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));

            }
        });

        LL_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                where_to_teach ="Online";

                LL_student_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_tutor_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_online.setBackgroundResource(R.drawable.edit_bgbutton);

                txt_student_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                txt_tutor_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                txt_online.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.colorWhite));


            }
        });

    }


    public void ContinueTakeClasses(View view) {
        if (sessionManager.isNetworkAvailable()) {

            LL_submit.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);

            addDetails();

        }else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }

       // startActivity(new Intent(TakeClassesActivity.this,HomeActvity.class));
    }

    public void backTake(View view) {
        onBackPressed();
        finish();
    }

    private void addDetails() {

       String Userid =  sessionManager.getUserID();
       String category_id = Preference.get(TakeClassesActivity.this, Preference.KEY_tutor_category_id);
       String sub_id = Preference.get(TakeClassesActivity.this, Preference.KEY_tutor_category_sub_id);

        MultipartBody.Part imgFile = null;
        if (UploadProfilePicActivity.imageFilePath == null) {
        } else {
            RequestBody requestFileOne = RequestBody.create(MediaType.parse("image/*"), UploadProfilePicActivity.imageFilePath);
            imgFile = MultipartBody.Part.createFormData("profile_image", UploadProfilePicActivity.imageFilePath.getName(), requestFileOne);
        }

        RequestBody Userid_main = RequestBody.create(MediaType.parse("text/plain"), Userid);
        RequestBody about_main = RequestBody.create(MediaType.parse("text/plain"), about);
        RequestBody Dob_main = RequestBody.create(MediaType.parse("text/plain"), Dob);
        RequestBody Gender_main = RequestBody.create(MediaType.parse("text/plain"), Gender);
        RequestBody where_to_teach_main = RequestBody.create(MediaType.parse("text/plain"), where_to_teach);
        RequestBody tutorFor_main = RequestBody.create(MediaType.parse("text/plain"), tutorFor);
        RequestBody Category_id = RequestBody.create(MediaType.parse("text/plain"), category_id);
        RequestBody Sub_category = RequestBody.create(MediaType.parse("text/plain"), sub_id);
        RequestBody Subject = RequestBody.create(MediaType.parse("text/plain"), "Math");
        RequestBody Check_status = RequestBody.create(MediaType.parse("text/plain"), "1");

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_student_details(Userid_main,about_main,Dob_main,Gender_main,where_to_teach_main,tutorFor_main,Category_id,
                        Sub_category,Subject,Check_status,imgFile);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    progressBar.setVisibility(View.GONE);
                    LL_submit.setEnabled(true);

                    JSONObject jsonObject = new JSONObject(response.body().string());

                    String status   = jsonObject.getString ("status");
                    String message = jsonObject.getString("message");

                    if (status.equalsIgnoreCase("1")) {

                        Toast.makeText(TakeClassesActivity.this, message, Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(TakeClassesActivity.this,HomeActvity.class));

                    } else {

                        Toast.makeText(TakeClassesActivity.this, result, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {

                    Toast.makeText(TakeClassesActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                } catch (IOException e) {

                    Toast.makeText(TakeClassesActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                LL_submit.setEnabled(true);
                Toast.makeText(TakeClassesActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}