package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.share.internal.ShareConstants;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.model.Get_detailsModel;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LessonSummaryActivity extends AppCompatActivity {
    String PaymentType = "cash";
    /* access modifiers changed from: private */
    public AlertDialog alertDialog;
    private Button btn_no;
    private Button btn_yes;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private View promptsView;
    String result = "";
    private SessionManager sessionManager;
    private TextView total_Price;
    /* access modifiers changed from: private */
    public TextView tutor_Old_year;
    private TextView tutor_distance;
    /* access modifiers changed from: private */
    public TextView tutor_gender;
    /* access modifiers changed from: private */
    public TextView tutor_name;
    private TextView txt_pkage_price;
    private TextView txt_pkage_time;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_lesson_summary);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.tutor_name = (TextView) findViewById(R.id.tutor_name);
        this.tutor_gender = (TextView) findViewById(R.id.tutor_gender);
        this.tutor_distance = (TextView) findViewById(R.id.tutor_distance);
        this.tutor_Old_year = (TextView) findViewById(R.id.tutor_Old_year);
        this.txt_pkage_time = (TextView) findViewById(R.id.txt_pkage_time);
        this.txt_pkage_price = (TextView) findViewById(R.id.txt_pkage_price);
        this.total_Price = (TextView) findViewById(R.id.total_Price);
        this.sessionManager = new SessionManager((Activity) this);
        String Pakage_time = Preference.get(this, Preference.key_Pakage);
        String Pakage_price = Preference.get(this, Preference.key_Pakage_price);
        this.txt_pkage_time.setText(Pakage_time);
        TextView textView = this.txt_pkage_price;
        textView.setText("$" + Pakage_price);
        TextView textView2 = this.total_Price;
        textView2.setText("Total $" + Pakage_price);
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getSummeryApi();
            return;
        }
        Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
    }

    public void summaryBackInit(View view) {
        onBackPressed();
        finish();
    }

    public void checkout(View view) {
        AlertDaliog();
    }

    private void getSummeryApi() {
        RetrofitClients.getInstance().getApi().get_student_details(Preference.get(this, Preference.KEY_Tutor_id)).enqueue(new Callback<Get_detailsModel>() {
            public void onResponse(Call<Get_detailsModel> call, Response<Get_detailsModel> response) {
                try {
                    Get_detailsModel finallyPr = response.body();
                    LessonSummaryActivity.this.progressBar.setVisibility(View.GONE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        String userName = finallyPr.getResult().getTutorDetails().getUsername();
                        String profileImage = finallyPr.getResult().getProfileImage();
                        String Gender = finallyPr.getResult().getGender();
                        String lat = finallyPr.getResult().getTutorDetails().getLat();
                        String lon = finallyPr.getResult().getTutorDetails().getLon();
                        String dob = finallyPr.getResult().getDob();
                        String perHourPayment = finallyPr.getResult().getPerHourPayment();
                        String education = finallyPr.getResult().getEducation();
                        String language = finallyPr.getResult().getLanguage();
                        String certification = finallyPr.getResult().getCertification();
                        String affiliations = finallyPr.getResult().getAffiliations();
                        LessonSummaryActivity.this.tutor_name.setText(userName);
                        LessonSummaryActivity.this.tutor_gender.setText(Gender);
                        Get_detailsModel get_detailsModel = finallyPr;
                        LessonSummaryActivity.this.tutor_Old_year.setText("24 year");
                        String str = status;
                        Toast.makeText(LessonSummaryActivity.this, "Success", Toast.LENGTH_LONG).show();
                        return;
                    }
                    String str2 = status;
                    Toast.makeText(LessonSummaryActivity.this, message, Toast.LENGTH_LONG).show();
                    LessonSummaryActivity.this.progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<Get_detailsModel> call, Throwable t) {
                LessonSummaryActivity.this.progressBar.setVisibility(View.GONE);
            }
        });
    }

    /* access modifiers changed from: private */
    public void BookingApiMethod() {
        String UserId = Preference.get(this, Preference.KEY_USER_ID);
        String TutorId = Preference.get(this, Preference.KEY_Tutor_id);
        String reserve_type = Preference.get(this, Preference.KEY_reserve_type);
        String TimeZone = Preference.get(this, Preference.KEY_time_zone);
        String StartTime = Preference.get(this, Preference.KEY_start_date);
        String Pakage_type = Preference.get(this, Preference.key_Pakage);
        String Pakage_price = Preference.get(this, Preference.key_Pakage_price);
        RetrofitClients.getInstance().getApi().tutor_booking(TutorId, UserId, reserve_type, ReserveSpotActivity.monday.toString(), ReserveSpotActivity.tuesday.toString(), ReserveSpotActivity.wednesday.toString(), ReserveSpotActivity.thursday.toString(), ReserveSpotActivity.friday.toString(), ReserveSpotActivity.saturday.toString(), ReserveSpotActivity.sunday.toString(), TimeZone, StartTime, Pakage_type, Pakage_price, this.PaymentType).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LessonSummaryActivity.this.progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String string = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    LessonSummaryActivity.this.result = jsonObject.getString("result");
                    if (status.equalsIgnoreCase("1")) {
                        LessonSummaryActivity.this.startActivity(new Intent(LessonSummaryActivity.this, HomeActvity.class));
                    } else {
                        Toast.makeText(LessonSummaryActivity.this, LessonSummaryActivity.this.result, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    LessonSummaryActivity lessonSummaryActivity = LessonSummaryActivity.this;
                    Toast.makeText(lessonSummaryActivity, lessonSummaryActivity.result, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e2) {
                    LessonSummaryActivity lessonSummaryActivity2 = LessonSummaryActivity.this;
                    Toast.makeText(lessonSummaryActivity2, lessonSummaryActivity2.result, Toast.LENGTH_LONG).show();
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LessonSummaryActivity.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(LessonSummaryActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void AlertDaliog() {
        LayoutInflater from = LayoutInflater.from(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.alert_dailoge, (ViewGroup) null);
        this.promptsView = inflate;
        this.btn_no = (Button) inflate.findViewById(R.id.btn_no);
        this.btn_yes = (Button) this.promptsView.findViewById(R.id.btn_yes);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(this.promptsView);
        this.btn_yes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LessonSummaryActivity.this.BookingApiMethod();
                LessonSummaryActivity.this.alertDialog.dismiss();
            }
        });
        this.btn_no.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LessonSummaryActivity.this.alertDialog.dismiss();
            }
        });
        AlertDialog create = alertDialogBuilder.create();
        this.alertDialog = create;
        create.show();
    }
}
