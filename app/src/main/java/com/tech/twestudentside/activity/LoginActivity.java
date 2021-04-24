package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tech.twestudentside.R;
import com.tech.twestudentside.GPSTracker;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.utils.Api;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "fireBaseToken";
    /* access modifiers changed from: private */
    public LinearLayout LL_submit;
    private RelativeLayout RR_google_login;
    RelativeLayout RR_loign_faceBook;
    private String android_id;
    private EditText edt_email;
    private EditText edt_password;
    String email = "";
    /* access modifiers changed from: private */
    public GoogleApiClient googleApiClient;
    GPSTracker gpsTracker;
    String latitude = "";
    LoginButton loginButton;
    String longitude = "";
    FirebaseAuth mAuth;
    CallbackManager mCallbackManager;
    String password = "";
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    String result = "";
    /* access modifiers changed from: private */
    public SessionManager sessionManager;
    private SignInButton signInButton;
    String token = "";
    private TextView tv_forgot_password;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView((int) R.layout.activity_login);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            public void onComplete(Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w(LoginActivity.TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }
                LoginActivity.this.token = task.getResult();
                Log.e("token", LoginActivity.this.token);
            }
        });
        try {
            for (Signature signature : getPackageManager().getPackageInfo(getPackageName(), 64).signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i(TAG, "printHashKey() Hash Key: " + new String(Base64.encode(md.digest(), 0)));
            }
        } catch (NoSuchAlgorithmException e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_LONG).show();
        } catch (Exception e2) {
            Toast.makeText(this, "" + e2, Toast.LENGTH_LONG).show();
        }
        this.android_id = Settings.Secure.getString(getContentResolver(), "android_id");
        setUi();
        this.RR_google_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(LoginActivity.this.googleApiClient), 1);
            }
        });
        this.mAuth = FirebaseAuth.getInstance();
        this.googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()).build();
        this.RR_loign_faceBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.loginButton.performClick();
            }
        });
        this.mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton2 = (LoginButton) findViewById(R.id.loginButton);
        this.loginButton = loginButton2;
        loginButton2.setReadPermissions("email", "public_profile");
        this.loginButton.registerCallback(this.mCallbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "facebook:onSuccess:" + loginResult);
                LoginActivity.this.handleFacebookAccessToken(loginResult.getAccessToken());
            }

            public void onCancel() {
                Toast.makeText(LoginActivity.this, "btnCancel", Toast.LENGTH_LONG).show();
                Log.d("TAG", "facebook:onCancel");
            }

            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Btnerrror", Toast.LENGTH_LONG).show();
                Log.d("TAG", "facebook:onError", error);
            }
        });
        GPSTracker gPSTracker = new GPSTracker(this);
        this.gpsTracker = gPSTracker;
        if (gPSTracker.canGetLocation()) {
            this.latitude = String.valueOf(this.gpsTracker.getLatitude());
            this.longitude = String.valueOf(this.gpsTracker.getLongitude());
            return;
        }
        this.gpsTracker.showSettingsAlert();
    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = this.mAuth.getCurrentUser();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            handleSignInResult(Auth.GoogleSignInApi.getSignInResultFromIntent(data));
        } else {
            this.mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result2) {
        if (result2.isSuccess()) {
            GoogleSignInAccount account = result2.getSignInAccount();
            String UsernAME = account.getDisplayName();
            String email2 = account.getEmail();
            String SocialId = account.getId();
            Uri photoUrl = account.getPhotoUrl();
            if (this.sessionManager.isNetworkAvailable()) {
                this.progressBar.setVisibility(View.VISIBLE);
                SocialLoginMethod(UsernAME, email2, "123456789", SocialId);
                return;
            }
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_LONG).show();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    /* access modifiers changed from: private */
    public void handleFacebookAccessToken(AccessToken token2) {
        Log.d("TAG", "handleFacebookAccessToken:" + token2);
        this.mAuth.signInWithCredential(FacebookAuthProvider.getCredential(token2.getToken())).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = LoginActivity.this.mAuth.getCurrentUser();
                    String UsernAME = user.getDisplayName();
                    String email = user.getEmail();
                    String SocialId = user.getUid();
                    Uri photoUrl = user.getPhotoUrl();
                    if (LoginActivity.this.sessionManager.isNetworkAvailable()) {
                        Preference.save(LoginActivity.this, Preference.KEYType_login, Api.social_login);
                        LoginActivity.this.progressBar.setVisibility(View.VISIBLE);
                        LoginActivity.this.SocialLoginMethod(UsernAME, email, "123456789", SocialId);
                        return;
                    }
                    Toast.makeText(LoginActivity.this, R.string.checkInternet, Toast.LENGTH_LONG).show();
                    return;
                }
                LoginActivity loginActivity = LoginActivity.this;
                Toast.makeText(loginActivity, "" + task.getException(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setUi() {
        this.RR_loign_faceBook = (RelativeLayout) findViewById(R.id.RR_loign_faceBook);
        this.loginButton = (LoginButton) findViewById(R.id.loginButton);
        this.edt_email = (EditText) findViewById(R.id.edt_email);
        this.edt_password = (EditText) findViewById(R.id.edt_password);
        this.LL_submit = (LinearLayout) findViewById(R.id.LL_submit);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.RR_google_login = (RelativeLayout) findViewById(R.id.RR_google_login);
        this.sessionManager = new SessionManager((Activity) this);
        TextView textView = (TextView) findViewById(R.id.tv_forgot_password);
        this.tv_forgot_password = textView;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            }
        });
    }

    public void loginInit(View view) {
        validation();
    }

    public void signupInit(View view) {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    private void validation() {
        this.email = this.edt_email.getText().toString();
        this.password = this.edt_password.getText().toString();
        if (!isValidEmail(this.email)) {
            Toast.makeText(this, "Please Enter email.", Toast.LENGTH_LONG).show();
        } else if (this.password.equalsIgnoreCase("")) {
            Toast.makeText(this, "Please Enter Password.", Toast.LENGTH_LONG).show();
        } else if (this.sessionManager.isNetworkAvailable()) {
            this.LL_submit.setEnabled(false);
            this.progressBar.setVisibility(View.VISIBLE);
            loginMethod();
        } else {
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void loginMethod() {
        RetrofitClients.getInstance().getApi().login(this.email, this.password, "Student", this.latitude, this.longitude, this.token).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LoginActivity.this.progressBar.setVisibility(View.GONE);
                    LoginActivity.this.LL_submit.setEnabled(true);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    LoginActivity.this.result = jsonObject.getString("result");
                    JSONObject resultOne = jsonObject.getJSONObject("result");
                    String UserId = resultOne.getString("id");
                    String check_status = resultOne.getString("check_status");
                    if (status.equalsIgnoreCase("1")) {
                        Preference.save(LoginActivity.this, Preference.KEY_USER_ID, UserId);
                        LoginActivity.this.sessionManager.saveUserId(UserId);
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                        if (check_status.equalsIgnoreCase("1")) {
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this.getApplicationContext(), HomeActvity.class));
                            LoginActivity.this.finish();
                            return;
                        }
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this.getApplicationContext(), CategorySelectActivity.class));
                        LoginActivity.this.finish();
                        return;
                    }
                    Toast.makeText(LoginActivity.this, LoginActivity.this.result, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    LoginActivity loginActivity = LoginActivity.this;
                    Toast.makeText(loginActivity, loginActivity.result, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e2) {
                    LoginActivity loginActivity2 = LoginActivity.this;
                    Toast.makeText(loginActivity2, loginActivity2.result, Toast.LENGTH_LONG).show();
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LoginActivity.this.progressBar.setVisibility(View.GONE);
                LoginActivity.this.LL_submit.setEnabled(true);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void SocialLoginMethod(String UserName, String email2, String Mobile, String SocialId) {
        RetrofitClients.getInstance().getApi().Social_login(UserName, email2, Mobile, "Student", this.latitude, this.longitude, SocialId, this.token).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    LoginActivity.this.progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String string = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    LoginActivity.this.result = jsonObject.getString("result");
                    JSONObject resultOne = jsonObject.getJSONObject("result");
                    String UserId = resultOne.getString("id");
                    String check_status = resultOne.getString("check_status");
                    if (status.equalsIgnoreCase("1")) {
                        Preference.save(LoginActivity.this, Preference.KEY_USER_ID, UserId);
                        LoginActivity.this.sessionManager.saveUserId(UserId);
                        Preference.save(LoginActivity.this, Preference.KEYType_login, "login");
                        if (check_status.equalsIgnoreCase("1")) {
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this.getApplicationContext(), HomeActvity.class));
                            LoginActivity.this.finish();
                            return;
                        }
                        LoginActivity.this.startActivity(new Intent(LoginActivity.this.getApplicationContext(), CategorySelectActivity.class));
                        LoginActivity.this.finish();
                        return;
                    }
                    Toast.makeText(LoginActivity.this, LoginActivity.this.result, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    LoginActivity loginActivity = LoginActivity.this;
                    Toast.makeText(loginActivity, loginActivity.result, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } catch (IOException e2) {
                    LoginActivity loginActivity2 = LoginActivity.this;
                    Toast.makeText(loginActivity2, loginActivity2.result, Toast.LENGTH_LONG).show();
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LoginActivity.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
