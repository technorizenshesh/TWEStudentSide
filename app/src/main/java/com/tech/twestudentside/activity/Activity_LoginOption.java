package com.tech.twestudentside.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
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
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
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

public class Activity_LoginOption extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "fireBaseToken";
    private RelativeLayout RR_google;
    private RelativeLayout RR_google_login;
    private RelativeLayout RR_loign_faceBook;
    /* access modifiers changed from: private */
    public GoogleApiClient googleApiClient;
    LoginButton loginButton;
    FirebaseAuth mAuth;
    CallbackManager mCallbackManager;
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    String result = "";
    /* access modifiers changed from: private */
    public SessionManager sessionManager;
    private SignInButton signInButton;
    String token = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView(R.layout.activity__login_option);
/*        try {
            for (Signature signature : getPackageManager().getPackageInfo(getPackageName(), 64).signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.i(TAG, "printHashKey() Hash Key: " + new String(Base64.encode(md.digest(), 0)));
            }
        } catch (NoSuchAlgorithmException e) {

            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        } catch (Exception e2) {
            Toast.makeText(this, "" + e2, Toast.LENGTH_SHORT).show();
        }*/

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            public void onComplete(Task<String> task) {
                if (!task.isSuccessful()) {
                    Log.w(Activity_LoginOption.TAG, "Fetching FCM registration token failed", task.getException());
                    return;
                }
                Activity_LoginOption.this.token = task.getResult();
                Log.e("token", Activity_LoginOption.this.token);
            }
        });
        this.RR_google = (RelativeLayout) findViewById(R.id.RR_google);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.RR_loign_faceBook = (RelativeLayout) findViewById(R.id.RR_loign_faceBook);
        this.sessionManager = new SessionManager((Activity) this);
        this.RR_google.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Activity_LoginOption.this.startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(Activity_LoginOption.this.googleApiClient), 1);
            }
        });
        this.RR_loign_faceBook.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Activity_LoginOption.this.loginButton.performClick();
            }
        });
        this.mAuth = FirebaseAuth.getInstance();
        this.googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()).build();
        this.mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton2 = (LoginButton) findViewById(R.id.loginButton);
        this.loginButton = loginButton2;
        loginButton2.setReadPermissions("email", "public_profile");
        this.loginButton.registerCallback(this.mCallbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "facebook:onSuccess:" + loginResult);
                Activity_LoginOption.this.handleFacebookAccessToken(loginResult.getAccessToken());
            }

            public void onCancel() {
                Toast.makeText(Activity_LoginOption.this, "btnCancel", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "facebook:onCancel");
            }

            public void onError(FacebookException error) {
                Toast.makeText(Activity_LoginOption.this, "Btnerrror", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "facebook:onError", error);
            }
        });
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
            String email = account.getEmail();
            String SocialId = account.getId();
            Uri photoUrl = account.getPhotoUrl();
            if (this.sessionManager.isNetworkAvailable()) {
                this.progressBar.setVisibility(View.VISIBLE);
                SocialLoginMethod(UsernAME, email, "123456789", SocialId);
                return;
            }
            Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    /* access modifiers changed from: private */
    public void handleFacebookAccessToken(AccessToken token2) {
        Log.d("TAG", "handleFacebookAccessToken:" + token2);
        this.mAuth.signInWithCredential(FacebookAuthProvider.getCredential(token2.getToken())).addOnCompleteListener((Activity) this, new OnCompleteListener<AuthResult>() {
            public void onComplete(Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = Activity_LoginOption.this.mAuth.getCurrentUser();
                    String UsernAME = user.getDisplayName();
                    String email = user.getEmail();
                    String SocialId = user.getUid();
                    Uri photoUrl = user.getPhotoUrl();
                    if (Activity_LoginOption.this.sessionManager.isNetworkAvailable()) {
                        Activity_LoginOption.this.progressBar.setVisibility(View.VISIBLE);
                        Activity_LoginOption.this.SocialLoginMethod(UsernAME, email, "123456789", SocialId);
                        return;
                    }
                    Toast.makeText(Activity_LoginOption.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                    return;
                }
                Activity_LoginOption activity_LoginOption = Activity_LoginOption.this;
                Toast.makeText(activity_LoginOption, "" + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void continueEmailInit(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    /* access modifiers changed from: private */
    public void SocialLoginMethod(String UserName, String email, String Mobile, String SocialId) {
        RetrofitClients.getInstance().getApi().Social_login(UserName, email, Mobile, "Student", "25.00", "25.00", SocialId, this.token).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Activity_LoginOption.this.progressBar.setVisibility(View.GONE);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    Activity_LoginOption.this.result = jsonObject.getString("result");
                    JSONObject resultOne = jsonObject.getJSONObject("result");
                    String UserId = resultOne.getString("id");
                    String check_status = resultOne.getString("check_status");
                    if (status.equalsIgnoreCase("1")) {
                        Activity_LoginOption.this.sessionManager.saveUserId(UserId);
                        Preference.save(Activity_LoginOption.this, Preference.KEYType_login, Api.social_login);
                        Preference.save(Activity_LoginOption.this, Preference.KEY_USER_ID, UserId);
                        Toast.makeText(Activity_LoginOption.this, message, Toast.LENGTH_SHORT).show();
                        if (check_status.equalsIgnoreCase("1")) {
                            Activity_LoginOption.this.startActivity(new Intent(Activity_LoginOption.this.getApplicationContext(), HomeActvity.class));
                            Activity_LoginOption.this.finish();
                            return;
                        }
                        Activity_LoginOption.this.startActivity(new Intent(Activity_LoginOption.this.getApplicationContext(), CategorySelectActivity.class));
                        Activity_LoginOption.this.finish();
                        return;
                    }
                    Toast.makeText(Activity_LoginOption.this, Activity_LoginOption.this.result, Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Activity_LoginOption activity_LoginOption = Activity_LoginOption.this;
                    Toast.makeText(activity_LoginOption, activity_LoginOption.result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                } catch (IOException e2) {
                    Activity_LoginOption activity_LoginOption2 = Activity_LoginOption.this;
                    Toast.makeText(activity_LoginOption2, activity_LoginOption2.result, Toast.LENGTH_SHORT).show();
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Activity_LoginOption.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(Activity_LoginOption.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
