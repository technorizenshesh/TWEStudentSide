package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.activity.AttendanceActivity;
import com.tech.twestudentside.activity.BookingHistoryStatus;
import com.tech.twestudentside.activity.ContactUsActivity;
import com.tech.twestudentside.activity.LoginActivity;
import com.tech.twestudentside.activity.PaymentStatementActivity;
import com.tech.twestudentside.activity.PreferredPaymentMethodActivity;
import com.tech.twestudentside.activity.PrivacyPolicy;
import com.tech.twestudentside.activity.TermsCondition;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.utils.Api;
import com.tech.twestudentside.utils.SessionManager;

public class AccountFragment extends Fragment implements View.OnClickListener {
    ImageView iv_back;
    LinearLayout layout_walletId;
    FragmentListener listener;
    LinearLayout llProfile;
    LinearLayout ll_booking_history_status;
    FirebaseAuth mAuth;
    LinearLayout myClassesId;
    LinearLayout my_attendanceLayout;
    TextView my_attendancetxt;
    LinearLayout my_paymentId;
    TextView my_txtpaymentId;
    /* access modifiers changed from: private */
    public SessionManager sessionManager;
    TextView txt_logout;
    TextView txt_myclassesId;
    TextView txt_walletId;
    LinearLayout ll_privacy_policy;
    LinearLayout ll_terms_condition;
    LinearLayout ll_contact_us;


    public AccountFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        this.sessionManager = new SessionManager((Activity) getActivity());
        this.txt_logout = (TextView) view.findViewById(R.id.txt_logout);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.myClassesId);
        this.myClassesId = linearLayout;
        linearLayout.setOnClickListener(this);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.my_paymentId);
        ll_booking_history_status= view.findViewById(R.id.ll_booking_history_status);
        this.my_paymentId = linearLayout2;
        linearLayout2.setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(R.id.my_txtpaymentId);
        this.my_txtpaymentId = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.txt_myclassesId);
        this.txt_myclassesId = textView2;
        textView2.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(this);
        LinearLayout linearLayout3 = (LinearLayout) view.findViewById(R.id.my_attendanceLayout);
        this.my_attendanceLayout = linearLayout3;
        linearLayout3.setOnClickListener(this);
        TextView textView3 = (TextView) view.findViewById(R.id.my_attendancetxt);
        this.my_attendancetxt = textView3;
        textView3.setOnClickListener(this);
        LinearLayout linearLayout4 = (LinearLayout) view.findViewById(R.id.layout_walletId);
        this.layout_walletId = linearLayout4;
        linearLayout4.setOnClickListener(this);
        this.txt_walletId = (TextView) view.findViewById(R.id.txt_walletId);
        this.llProfile = (LinearLayout) view.findViewById(R.id.llProfile);
        this.ll_privacy_policy = (LinearLayout) view.findViewById(R.id.ll_privacy_policy);
        this.ll_terms_condition = (LinearLayout) view.findViewById(R.id.ll_terms_condition);
        this.ll_contact_us = (LinearLayout) view.findViewById(R.id.ll_contact_us);
        this.txt_walletId.setOnClickListener(this);
        this.txt_logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Preference.get(AccountFragment.this.getActivity(), Preference.KEYType_login).equalsIgnoreCase(Api.social_login)) {
                    AccountFragment.this.sessionManager.logoutUser();
                    Preference.clearPreference(AccountFragment.this.getActivity());
                    FirebaseAuth firebaseAuth = AccountFragment.this.mAuth;
                    FirebaseAuth.getInstance().signOut();
                    LoginManager.getInstance().logOut();
                    AccountFragment.this.startActivity(new Intent(AccountFragment.this.getActivity(), LoginActivity.class));
                    return;
                }
                AccountFragment.this.sessionManager.logoutUser();
                Preference.clearPreference(AccountFragment.this.getActivity());
                AccountFragment.this.startActivity(new Intent(AccountFragment.this.getActivity(), LoginActivity.class));
            }
        });

        this.llProfile.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AccountFragment.this.listener.click(new AccountDetails(AccountFragment.this.listener));
            }
        });

        this.ll_booking_history_status.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), BookingHistoryStatus.class));
            }
        });

        ll_privacy_policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), PrivacyPolicy.class));
            }
        });

        ll_terms_condition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), TermsCondition.class));
            }
        });

        ll_contact_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(),ContactUsActivity.class));
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameContainer, fragment).commit();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                FragmentListener fragmentListener = this.listener;
                fragmentListener.click(new HomeFragment(fragmentListener));
                return;
            case R.id.layout_walletId:
                startActivity(new Intent(getActivity(), PreferredPaymentMethodActivity.class));
                return;
            case R.id.myClassesId:
                FragmentListener fragmentListener2 = this.listener;
                fragmentListener2.click(new RequestFragment(fragmentListener2));
                return;
            case R.id.my_attendanceLayout:
                startActivity(new Intent(getActivity(), AttendanceActivity.class));
                return;
            case R.id.my_attendancetxt:
                startActivity(new Intent(getActivity(), AttendanceActivity.class));
                return;
            case R.id.my_paymentId:
                startActivity(new Intent(getActivity(), PaymentStatementActivity.class));
                return;
            case R.id.my_txtpaymentId:
                startActivity(new Intent(getActivity(), PaymentStatementActivity.class));
                return;
            case R.id.txt_myclassesId:
                FragmentListener fragmentListener3 = this.listener;
                fragmentListener3.click(new RequestFragment(fragmentListener3));
                return;
            case R.id.txt_walletId:
                startActivity(new Intent(getActivity(), PreferredPaymentMethodActivity.class));
                return;
            default:
                return;
        }
    }
}
