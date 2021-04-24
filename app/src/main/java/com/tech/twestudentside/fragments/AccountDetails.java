package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.myprofile_model;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetails extends Fragment {
    ImageView img_profile;
    ImageView iv_back;
    FragmentListener listener;
    ProgressBar progressBar;
    private SessionManager sessionManager;
    TextView txt_address;
    TextView txt_email;
    TextView txt_name;
    TextView txt_phone;

    public AccountDetails(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_profile, container, false);
        this.iv_back = (ImageView) view.findViewById(R.id.iv_back);
        this.txt_name = (TextView) view.findViewById(R.id.txt_name);
        this.txt_email = (TextView) view.findViewById(R.id.txt_email);
        this.txt_phone = (TextView) view.findViewById(R.id.txt_phone);
        this.txt_address = (TextView) view.findViewById(R.id.txt_address);
        this.img_profile = (ImageView) view.findViewById(R.id.img_profile);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.sessionManager = new SessionManager((Activity) getActivity());
        this.iv_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AccountDetails.this.listener.click(new HomeFragment(AccountDetails.this.listener));
            }
        });
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            myProfile();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void myProfile() {
        RetrofitClients.getInstance().getApi().get_profile(Preference.get(getActivity(), Preference.KEY_USER_ID)).enqueue(new Callback<myprofile_model>() {
            public void onResponse(Call<myprofile_model> call, Response<myprofile_model> response) {
                try {
                    AccountDetails.this.progressBar.setVisibility(View.GONE);
                    myprofile_model finallyPr = response.body();
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getStatus();
                    if (status.equalsIgnoreCase("1")) {
                        String Email = finallyPr.getResult().get(0).getEmail().toString();
                        String name = finallyPr.getResult().get(0).getUsername().toString();
                        String Phone = finallyPr.getResult().get(0).getMobile().toString();
                        String address = finallyPr.getResult().get(0).getAddressDetails().getAddress();
                        String profile_image = finallyPr.getResult().get(0).getProfileImage();
                        AccountDetails.this.txt_name.setText(name);
                        AccountDetails.this.txt_email.setText(Email);
                        AccountDetails.this.txt_phone.setText(Phone);
                        AccountDetails.this.txt_address.setText(address);
                        if (profile_image != null) {
                            ((RequestBuilder) Glide.with(AccountDetails.this.getActivity()).load(profile_image).placeholder((int) R.drawable.logo)).into(AccountDetails.this.img_profile);
                        }
                        return;
                    }
                    Toast.makeText(AccountDetails.this.getActivity(), message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<myprofile_model> call, Throwable t) {
                AccountDetails.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(AccountDetails.this.getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
