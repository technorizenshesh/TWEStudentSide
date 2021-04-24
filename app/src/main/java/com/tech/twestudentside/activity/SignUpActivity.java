package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.tech.twestudentside.R;
import com.tech.twestudentside.GPSTracker;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    String Cpassword = "";
    /* access modifiers changed from: private */
    public LinearLayout LL_signUp;
    String Username = "";
    private String android_id;
    private EditText edt_confirm_password;
    private EditText edt_email;
    private EditText edt_name;
    private EditText edt_password;
    private EditText edt_phone;
    String email = "";
    GPSTracker gpsTracker;
    String latitude = "";
    String longitude = "";
    FusedLocationProviderClient mFusedLocationClient;
    String password = "";
    String phone = "";
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    String result = "";
    /* access modifiers changed from: private */
    public SessionManager sessionManager;
    double userLat = 0.0d;
    double userLong = 0.0d;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView(R.layout.activity_sign_up);
        this.android_id = Settings.Secure.getString(getContentResolver(), "android_id");
        GPSTracker gPSTracker = new GPSTracker(this);
        this.gpsTracker = gPSTracker;
        if (gPSTracker.canGetLocation()) {
            this.latitude = String.valueOf(this.gpsTracker.getLatitude());
            this.longitude = String.valueOf(this.gpsTracker.getLongitude());
        } else {
            this.gpsTracker.showSettingsAlert();
        }
        setUi();
    }

    private void setUi() {
        this.edt_name = (EditText) findViewById(R.id.edt_name);
        this.edt_phone = (EditText) findViewById(R.id.edt_phone);
        this.edt_email = (EditText) findViewById(R.id.edt_email);
        this.edt_password = (EditText) findViewById(R.id.edt_password);
        this.edt_confirm_password = (EditText) findViewById(R.id.edt_confirm_password);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.LL_signUp = (LinearLayout) findViewById(R.id.LL_signUp);
        this.sessionManager = new SessionManager((Activity) this);
    }

    public void SignUPInit(View view) {
        validation();
    }

    public void backLoginInit(View view) {
        startActivity(new Intent(this, LoginActivity.class));
    }

    private void validation() {
        this.Username = this.edt_name.getText().toString();
        this.phone = this.edt_phone.getText().toString();
        this.email = this.edt_email.getText().toString();
        this.password = this.edt_password.getText().toString();
        this.Cpassword = this.edt_confirm_password.getText().toString();
        if (this.Username.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter name.", Toast.LENGTH_LONG).show();
        } else if (this.phone.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter phone.", Toast.LENGTH_LONG).show();
        } else if (!isValidEmail(this.email)) {
            Toast.makeText(this, "Please Enter email.", Toast.LENGTH_LONG).show();
        } else if (this.password.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Password.", Toast.LENGTH_LONG).show();
        } else if (this.Cpassword.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Confirm password.", Toast.LENGTH_LONG).show();
        } else if (!this.Cpassword.equalsIgnoreCase(this.password)) {
            Toast.makeText(this, "Don't match password.", Toast.LENGTH_LONG).show();
        } else {
            Preference.save(this, Preference.KEY_Mobile_number, this.phone);
            if (this.sessionManager.isNetworkAvailable()) {
                this.LL_signUp.setEnabled(false);
                this.progressBar.setVisibility(View.VISIBLE);
                signUpMethod();
                return;
            }
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void signUpMethod() {
        RetrofitClients.getInstance().getApi().signUp(this.Username, this.email, this.password, this.phone, "Student", this.latitude, this.longitude, this.android_id).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    SignUpActivity.this.progressBar.setVisibility(View.GONE);
                    SignUpActivity.this.LL_signUp.setEnabled(true);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    SignUpActivity.this.result = jsonObject.getString("result");
                    String UserId = jsonObject.getJSONObject("result").getString("id");
                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(SignUpActivity.this, message, Toast.LENGTH_LONG).show();
                        SignUpActivity.this.sessionManager.saveUserId(UserId);
                        SignUpActivity.this.startActivity(new Intent(SignUpActivity.this, Activity_otp.class));
                        return;
                    }
                    Toast.makeText(SignUpActivity.this, SignUpActivity.this.result, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    SignUpActivity signUpActivity = SignUpActivity.this;
                    Toast.makeText(signUpActivity, signUpActivity.result, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e2) {
                    SignUpActivity signUpActivity2 = SignUpActivity.this;
                    Toast.makeText(signUpActivity2, signUpActivity2.result, Toast.LENGTH_LONG).show();
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                SignUpActivity.this.progressBar.setVisibility(View.GONE);
                SignUpActivity.this.LL_signUp.setEnabled(true);
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showSettingDialogue() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) "Need Permissions");
        builder.setMessage((CharSequence) "This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton((CharSequence) "GOTO SETTINGS", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                SignUpActivity.this.openSetting();
            }
        });
        builder.setNegativeButton((CharSequence) "Cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    /* access modifiers changed from: private */
    public void openSetting() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", getPackageName(), (String) null));
        startActivityForResult(intent, 101);
    }
}
