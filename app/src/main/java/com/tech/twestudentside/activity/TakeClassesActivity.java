package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.facebook.places.model.PlaceFields;
import com.facebook.share.internal.ShareConstants;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TakeClassesActivity extends AppCompatActivity {
    private String Dob = "";
    private String Gender = "";
    /* access modifiers changed from: private */
    public LinearLayout LL_online;
    /* access modifiers changed from: private */
    public LinearLayout LL_student_home;
    /* access modifiers changed from: private */
    public LinearLayout LL_submit;
    /* access modifiers changed from: private */
    public LinearLayout LL_tutor_home;
    private String about = "";
    private String android_id;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    String result = "";
    private SessionManager sessionManager;
    private String tutorFor = "";
    /* access modifiers changed from: private */
    public TextView txt_online;
    /* access modifiers changed from: private */
    public TextView txt_student_home;
    /* access modifiers changed from: private */
    public TextView txt_tutor_home;
    String where_to_teach = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView(R.layout.activity_take_classes);
        this.LL_student_home = (LinearLayout) findViewById(R.id.LL_student_home);
        this.LL_tutor_home = (LinearLayout) findViewById(R.id.LL_tutor_home);
        this.LL_online = (LinearLayout) findViewById(R.id.LL_online);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.LL_submit = (LinearLayout) findViewById(R.id.LL_submit);
        this.sessionManager = new SessionManager((Activity) this);
        this.txt_student_home = (TextView) findViewById(R.id.txt_student_home);
        this.txt_tutor_home = (TextView) findViewById(R.id.txt_tutor_home);
        this.txt_online = (TextView) findViewById(R.id.txt_online);
        Intent intent = getIntent();
        if (intent != null) {
            this.Dob = intent.getStringExtra("DOB").toString();
            this.Gender = intent.getStringExtra("Gender").toString();
            this.about = intent.getStringExtra(PlaceFields.ABOUT).toString();
            this.tutorFor = intent.getStringExtra("tutorFor").toString();
        }
        this.LL_student_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TakeClassesActivity.this.where_to_teach = "Student_Home";
                TakeClassesActivity.this.LL_student_home.setBackgroundResource(R.drawable.edit_bgbutton);
                TakeClassesActivity.this.LL_tutor_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TakeClassesActivity.this.LL_online.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TakeClassesActivity.this.txt_student_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.colorWhite));
                TakeClassesActivity.this.txt_tutor_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                TakeClassesActivity.this.txt_online.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
            }
        });
        this.LL_tutor_home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TakeClassesActivity.this.where_to_teach = "Tutor_Home";
                TakeClassesActivity.this.LL_student_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TakeClassesActivity.this.LL_tutor_home.setBackgroundResource(R.drawable.edit_bgbutton);
                TakeClassesActivity.this.LL_online.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TakeClassesActivity.this.txt_student_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                TakeClassesActivity.this.txt_tutor_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.colorWhite));
                TakeClassesActivity.this.txt_online.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
            }
        });
        this.LL_online.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TakeClassesActivity.this.where_to_teach = "Online";
                TakeClassesActivity.this.LL_student_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TakeClassesActivity.this.LL_tutor_home.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TakeClassesActivity.this.LL_online.setBackgroundResource(R.drawable.edit_bgbutton);
                TakeClassesActivity.this.txt_student_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                TakeClassesActivity.this.txt_tutor_home.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.color_txtBlue));
                TakeClassesActivity.this.txt_online.setTextColor(ContextCompat.getColor(TakeClassesActivity.this, R.color.colorWhite));
            }
        });
    }

    public void ContinueTakeClasses(View view) {
        if (this.sessionManager.isNetworkAvailable()) {
            this.LL_submit.setEnabled(false);
            this.progressBar.setVisibility(View.VISIBLE);
            addDetails();
            return;
        }
        Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
    }

    public void backTake(View view) {
        onBackPressed();
        finish();
    }

    private void addDetails() {
        String Userid = this.sessionManager.getUserID();
        String category_id = Preference.get(this, Preference.KEY_tutor_category_id);
        String sub_id = Preference.get(this, Preference.KEY_tutor_category_sub_id);
        MultipartBody.Part imgFile = null;
        if (UploadProfilePicActivity.imageFilePath != null) {
            imgFile = MultipartBody.Part.createFormData("profile_image", UploadProfilePicActivity.imageFilePath.getName(), RequestBody.create(MediaType.parse("image/*"), UploadProfilePicActivity.imageFilePath));
        }
        RetrofitClients.getInstance().getApi().add_student_details(RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), Userid), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), this.about), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), this.Dob), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), this.Gender), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), this.where_to_teach), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), this.tutorFor), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), category_id), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), sub_id), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), "Math"), RequestBody.create(MediaType.parse(org.androidannotations.api.rest.MediaType.TEXT_PLAIN), "1"), imgFile).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    TakeClassesActivity.this.progressBar.setVisibility(View.GONE);
                    TakeClassesActivity.this.LL_submit.setEnabled(true);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(TakeClassesActivity.this, message, Toast.LENGTH_LONG).show();
                        TakeClassesActivity.this.startActivity(new Intent(TakeClassesActivity.this, HomeActvity.class));
                        return;
                    }
                    Toast.makeText(TakeClassesActivity.this, TakeClassesActivity.this.result, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    TakeClassesActivity takeClassesActivity = TakeClassesActivity.this;
                    Toast.makeText(takeClassesActivity, takeClassesActivity.result, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e2) {
                    TakeClassesActivity takeClassesActivity2 = TakeClassesActivity.this;
                    Toast.makeText(takeClassesActivity2, takeClassesActivity2.result, Toast.LENGTH_LONG).show();
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                TakeClassesActivity.this.progressBar.setVisibility(View.GONE);
                TakeClassesActivity.this.LL_submit.setEnabled(true);
                Toast.makeText(TakeClassesActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
