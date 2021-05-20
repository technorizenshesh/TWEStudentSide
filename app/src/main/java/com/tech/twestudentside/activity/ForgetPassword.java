package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.share.internal.ShareConstants;
import com.tech.twestudentside.R;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.io.IOException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {
    /* access modifiers changed from: private */
    public LinearLayout LL_submit;
    private String android_id;
    private EditText edt_email;
    private String email = "";
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private SessionManager sessionManager;
    private TextView txt_login;

    /*  */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        this.android_id = Settings.Secure.getString(getContentResolver(), "android_id");
        setUi();
    }

    private void setUi() {
        this.edt_email = (EditText) findViewById(R.id.edt_email);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.LL_submit = (LinearLayout) findViewById(R.id.LL_submit);
        this.txt_login = (TextView) findViewById(R.id.txt_login);
        this.sessionManager = new SessionManager((Activity) this);
        this.txt_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ForgetPassword.this.startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
            }
        });
        this.LL_submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ForgetPassword.this.validation();
            }
        });
    }

    /* access modifiers changed from: private */
    public void validation() {
        String obj = this.edt_email.getText().toString();
        this.email = obj;
        if (!isValidEmail(obj)) {
            Toast.makeText(this, "Please Enter email.", Toast.LENGTH_LONG).show();
        } else if (this.sessionManager.isNetworkAvailable()) {
            this.LL_submit.setEnabled(false);
            this.progressBar.setVisibility(View.VISIBLE);
            forgetMethod();
        } else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void forgetMethod() {
        RetrofitClients.getInstance().getApi().forgotPassword(this.email).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    if (status.equalsIgnoreCase("1")) {
                        ForgetPassword.this.LL_submit.setEnabled(true);
                        ForgetPassword.this.progressBar.setVisibility(View.GONE);
                        Toast.makeText(ForgetPassword.this, message, Toast.LENGTH_LONG).show();
                        ForgetPassword.this.startActivity(new Intent(ForgetPassword.this, LoginActivity.class));
                        return;
                    }
                    Toast.makeText(ForgetPassword.this, message, Toast.LENGTH_LONG).show();
                    ForgetPassword.this.progressBar.setVisibility(View.GONE);
                    ForgetPassword.this.LL_submit.setEnabled(true);
                    Toast.makeText(ForgetPassword.this, message, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                ForgetPassword.this.progressBar.setVisibility(View.GONE);
                ForgetPassword.this.LL_submit.setEnabled(true);
                Toast.makeText(ForgetPassword.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
