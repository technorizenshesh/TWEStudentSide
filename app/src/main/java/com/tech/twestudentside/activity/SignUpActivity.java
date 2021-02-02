package com.tech.twestudentside.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.tech.twestudentside.GPSTracker;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_phone;
    private EditText edt_email;
    private EditText edt_password;
    private EditText edt_confirm_password;
    private LinearLayout LL_signUp;

    private SessionManager sessionManager;
    private String android_id;
    private ProgressBar progressBar;

    String Username = "";
    String phone = "";
    String email = "";
    String password = "";
    String Cpassword = "";
    String result = "";
    double userLat = 0;
    double userLong = 0;

    FusedLocationProviderClient
            mFusedLocationClient;

    GPSTracker gpsTracker;

    String latitude="";
    String longitude="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_sign_up);

        //android device Id
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        gpsTracker=new GPSTracker(this);

        if(gpsTracker.canGetLocation()){

            latitude = String.valueOf(gpsTracker.getLatitude());
            longitude = String.valueOf(gpsTracker.getLongitude());

        }else{
            gpsTracker.showSettingsAlert();
        }

        setUi();
    }

    private void setUi() {
        edt_name=findViewById(R.id.edt_name);
        edt_phone=findViewById(R.id.edt_phone);
        edt_email=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);
        edt_confirm_password=findViewById(R.id.edt_confirm_password);
        progressBar=findViewById(R.id.progressBar);
        LL_signUp=findViewById(R.id.LL_signUp);
        sessionManager = new SessionManager(SignUpActivity.this);
    }

    public void SignUPInit(View view) {

       validation();
     //   startActivity(new Intent(SignUpActivity.this, Activity_otp.class));
    }

    public void backLoginInit(View view) {
        startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
    }

    private void validation() {

        Username = edt_name.getText().toString();
        phone = edt_phone.getText().toString();
        email = edt_email.getText().toString();
        password = edt_password.getText().toString();
        Cpassword = edt_confirm_password.getText().toString();

        if(Username.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter name.", Toast.LENGTH_SHORT).show();

        }else if(phone.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter phone.", Toast.LENGTH_SHORT).show();

        }else if(!isValidEmail(email))
        {
            Toast.makeText(this, "Please Enter email.", Toast.LENGTH_SHORT).show();

        }else if(password.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Password.", Toast.LENGTH_SHORT).show();

        }else if(Cpassword.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Confirm password.", Toast.LENGTH_SHORT).show();

        }else if(! Cpassword.equalsIgnoreCase(password))
        {
            Toast.makeText(this, "Don't match password.", Toast.LENGTH_SHORT).show();

        }else
        {
            Preference.save(SignUpActivity.this,Preference.KEY_Mobile_number,phone);

           if (sessionManager.isNetworkAvailable()) {

               LL_signUp.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);

               signUpMethod();

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    private void signUpMethod() {

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .signUp(Username,email,password,phone,"Student", latitude, longitude,android_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {

                    progressBar.setVisibility(View.GONE);
                    LL_signUp.setEnabled(true);

                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status   = jsonObject.getString ("status");
                    String message = jsonObject.getString("message");
                    result = jsonObject.getString("result");

                    JSONObject resultOne = jsonObject.getJSONObject("result");

                    String UserId = resultOne.getString("id");

                    if (status.equalsIgnoreCase("1")) {

                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_SHORT).show();

                        sessionManager.saveUserId(UserId);

                        startActivity(new Intent(SignUpActivity.this, Activity_otp.class));

                    } else {

                        Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                } catch (IOException e) {
                    Toast.makeText(SignUpActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                LL_signUp.setEnabled(true);
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showSettingDialogue() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                openSetting();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();

    }

    private void openSetting() {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

}