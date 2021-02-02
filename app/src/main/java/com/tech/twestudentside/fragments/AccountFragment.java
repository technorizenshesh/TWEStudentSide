package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.Activity_otp;
import com.tech.twestudentside.activity.AttendanceActivity;
import com.tech.twestudentside.activity.LoginActivity;
import com.tech.twestudentside.activity.MyClassActivity;
import com.tech.twestudentside.activity.PaymentStatementActivity;
import com.tech.twestudentside.activity.PreferredPaymentMethodActivity;
import com.tech.twestudentside.activity.SignUpActivity;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.utils.SessionManager;


public class AccountFragment extends Fragment implements View.OnClickListener {


    LinearLayout myClassesId,my_paymentId,my_attendanceLayout,layout_walletId;
    TextView txt_myclassesId,my_txtpaymentId,my_attendancetxt,txt_walletId;
    ImageView iv_back;

    TextView txt_logout;
    private SessionManager sessionManager;
    FragmentListener listener;
    FirebaseAuth mAuth;
    public AccountFragment(FragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_account, container, false);

        sessionManager = new SessionManager(getActivity());

        txt_logout=view.findViewById(R.id.txt_logout);
        myClassesId=view.findViewById(R.id.myClassesId);
        myClassesId.setOnClickListener(this);
        my_paymentId=view.findViewById(R.id.my_paymentId);
        my_paymentId.setOnClickListener(this);
        my_txtpaymentId=view.findViewById(R.id.my_txtpaymentId);
        my_txtpaymentId.setOnClickListener(this);
        txt_myclassesId=view.findViewById(R.id.txt_myclassesId);
        txt_myclassesId.setOnClickListener(this);
        iv_back=view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        my_attendanceLayout=view.findViewById(R.id.my_attendanceLayout);
        my_attendanceLayout.setOnClickListener(this);
        my_attendancetxt=view.findViewById(R.id.my_attendancetxt);
        my_attendancetxt.setOnClickListener(this);

        layout_walletId=view.findViewById(R.id.layout_walletId);
        layout_walletId.setOnClickListener(this);
        txt_walletId=view.findViewById(R.id.txt_walletId);
        txt_walletId.setOnClickListener(this);

        txt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserType = Preference.get(getActivity(),Preference.KEYType_login);
                if(UserType.equalsIgnoreCase("social_login"))
                {
                    sessionManager.logoutUser();
                    Preference.clearPreference(getActivity());
                    mAuth.getInstance().signOut();
                    LoginManager.getInstance().logOut();
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }else
                {
                    sessionManager.logoutUser();
                    Preference.clearPreference(getActivity());
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.myClassesId:
                listener.click(new RequestFragment(listener));
                break;


                    case R.id.txt_myclassesId:
                        listener.click(new RequestFragment(listener));
                        break;



            case R.id.my_paymentId:
                startActivity(new Intent(getActivity(), PaymentStatementActivity.class));
                break;


            case R.id.my_txtpaymentId:
                startActivity(new Intent(getActivity(), PaymentStatementActivity.class));
                break;


            case R.id.iv_back:
                listener.click(new HomeFragment(listener));

                break;


            case R.id.my_attendanceLayout:
                startActivity(new Intent(getActivity(), AttendanceActivity.class));
                break;


            case R.id.my_attendancetxt:
                startActivity(new Intent(getActivity(), AttendanceActivity.class));
                break;



            case R.id.layout_walletId:
                startActivity(new Intent(getActivity(), PreferredPaymentMethodActivity.class));
                break;


            case R.id.txt_walletId:
                startActivity(new Intent(getActivity(), PreferredPaymentMethodActivity.class));
                break;

        }

    }
}