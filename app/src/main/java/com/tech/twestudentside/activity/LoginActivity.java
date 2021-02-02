package com.tech.twestudentside.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private EditText edt_email;
    private EditText edt_password;
    private LinearLayout LL_submit;
    private TextView tv_forgot_password;
    String email="";
    String password="";

    private SessionManager sessionManager;
    private String android_id;
    private ProgressBar progressBar;
    private RelativeLayout RR_google_login;
    String result="";

    //Google SignIn
    private SignInButton signInButton;
    FirebaseAuth mAuth;
    private final static int RC_SIGN_IN = 1;
    private GoogleApiClient googleApiClient;

    private static final String TAG = "fireBaseToken";
    String token="";
    RelativeLayout RR_loign_faceBook;

    //FaceBook
    CallbackManager mCallbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);}
        setContentView(R.layout.activity_login);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();
                        Log.e("token",token);
                    }
                });

        //android device Id
        android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        setUi();

        RR_google_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, RC_SIGN_IN);

            }
        });

        //Google SignIn
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        //FaceBook
        RR_loign_faceBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginButton.performClick();
            }
        });

        mCallbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.loginButton);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("TAG", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }
            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "btnCancel", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "facebook:onCancel");
                // ...
            }
            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Btnerrror", Toast.LENGTH_SHORT).show();
                Log.d("TAG", "facebook:onError", error);
                // ...
            }
        });
    }

    //Google Login
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult( requestCode, resultCode, data );
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent( data );
            handleSignInResult( result );
        }else {
            mCallbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();

            String UsernAME=account.getDisplayName();
            String email=account.getEmail();
            String SocialId=account.getId();
            Uri Url=account.getPhotoUrl();

            if (sessionManager.isNetworkAvailable()) {

                progressBar.setVisibility(View.VISIBLE);

                SocialLoginMethod(UsernAME,email,"123456789",SocialId);

            }else {

                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }

        } else {

            Toast.makeText( this, "Login Unsuccessful", Toast.LENGTH_SHORT ).show();

        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //   Toast.makeText(Activity_LoginOption.this, ""+token, Toast.LENGTH_SHORT).show();

                            FirebaseUser user = mAuth.getCurrentUser();

                            String UsernAME=user.getDisplayName();
                            String email=user.getEmail();
                            String SocialId=user.getUid();
                            Uri Url=user.getPhotoUrl();

                            if (sessionManager.isNetworkAvailable()) {

                                Preference.save(LoginActivity.this,Preference.KEYType_login,"social_login");

                                progressBar.setVisibility(View.VISIBLE);

                                SocialLoginMethod(UsernAME,email,"123456789",SocialId);

                            }else {

                                Toast.makeText(LoginActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                            }


                        } else {

                            Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private void setUi() {
        RR_loign_faceBook=findViewById(R.id.RR_loign_faceBook);
        loginButton=findViewById(R.id.loginButton);
        edt_email=findViewById(R.id.edt_email);
        edt_password=findViewById(R.id.edt_password);
        LL_submit=findViewById(R.id.LL_submit);
        progressBar=findViewById(R.id.progressBar);
        RR_google_login=findViewById(R.id.RR_google_login);
        sessionManager = new SessionManager(LoginActivity.this);

        tv_forgot_password=findViewById(R.id.tv_forgot_password);
        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(LoginActivity.this, ForgetPassword.class));
            }
        });
    }

    public void loginInit(View view) {
        //startActivity(new Intent(LoginActivity.this, HomeActvity.class));
        validation();
    }

    public void signupInit(View view) {
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }


    private void validation() {

        email = edt_email.getText().toString();
        password = edt_password.getText().toString();

        if(!isValidEmail(email))
        {
            Toast.makeText(this, "Please Enter email.", Toast.LENGTH_SHORT).show();

        }else if(password.equalsIgnoreCase(""))
        {
            Toast.makeText(this, "Please Enter Password.", Toast.LENGTH_SHORT).show();

        }else
        {
            if (sessionManager.isNetworkAvailable()) {

                LL_submit.setEnabled(false);
                progressBar.setVisibility(View.VISIBLE);

                loginMethod();

            }else {
                Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }


    private void loginMethod() {

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .login(email,password,"Student","25.00","25.00",android_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    progressBar.setVisibility(View.GONE);
                    LL_submit.setEnabled(true);
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status   = jsonObject.getString ("status");
                    String message = jsonObject.getString("message");
                    result = jsonObject.getString("result");

                    JSONObject resultOne = jsonObject.getJSONObject("result");

                    String UserId = resultOne.getString("id");
                    String check_status = resultOne.getString("check_status");

                    if (status.equalsIgnoreCase("1")) {

                        Preference.save(LoginActivity.this,Preference.KEY_USER_ID,UserId);

                        sessionManager.saveUserId(UserId);

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        if(check_status.equalsIgnoreCase("1"))
                        {
                            startActivity(new Intent(getApplicationContext(),HomeActvity.class));
                            finish();

                        }else
                        {
                            startActivity(new Intent(getApplicationContext(),CategorySelectActivity.class));
                            finish();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {

                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                } catch (IOException e) {

                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                LL_submit.setEnabled(true);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void SocialLoginMethod(String UserName,String email,String Mobile,String SocialId) {

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .Social_login(UserName,email,Mobile,"Student","25.00","25.00",SocialId,token);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    progressBar.setVisibility(View.GONE);

                    JSONObject jsonObject = new JSONObject(response.body().string());

                    String status   = jsonObject.getString ("status");
                    String message = jsonObject.getString("message");

                    result = jsonObject.getString("result");

                    JSONObject resultOne = jsonObject.getJSONObject("result");

                    String UserId = resultOne.getString("id");

                    String check_status = resultOne.getString("check_status");

                    if (status.equalsIgnoreCase("1")) {

                        Preference.save(LoginActivity.this,Preference.KEY_USER_ID,UserId);

                        sessionManager.saveUserId(UserId);

                        Preference.save(LoginActivity.this,Preference.KEYType_login,"login");

                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();

                        if(check_status.equalsIgnoreCase("1"))
                        {
                            startActivity(new Intent(getApplicationContext(),HomeActvity.class));
                            finish();

                        }else
                        {
                            startActivity(new Intent(getApplicationContext(),CategorySelectActivity.class));
                            finish();
                        }

                    } else {
                        Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {

                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                } catch (IOException e) {

                    Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}