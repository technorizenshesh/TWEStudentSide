package com.tech.twestudentside.activity;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.tech.twestudentside.R;
import com.tech.twestudentside.databinding.ActivityAddAddressHomeBinding;


public class AddAddressHome extends AppCompatActivity {

    ActivityAddAddressHomeBinding binding;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding =  DataBindingUtil.setContentView(this, R.layout.activity_add_address_home);
        init();
    }

    private void init() {
        this.binding.LLSaveAddress.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                AddAddressHome.this.lambda$init$0$AddAddressHome(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$0$AddAddressHome(View v) {
        validation();
    }

    private void validation() {
        String obj = this.binding.edtAddressType.getText().toString();
        String obj2 = this.binding.edtAddress.getText().toString();
        String obj3 = this.binding.edtAddress.getText().toString();
    }
}
