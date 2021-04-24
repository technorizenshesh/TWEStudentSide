package com.tech.twestudentside.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.facebook.places.model.PlaceFields;
import com.tech.twestudentside.R;

public class TutorSelectionActivity extends AppCompatActivity {
    private String Dob = "";
    private String Gender = "";
    /* access modifiers changed from: private */
    public LinearLayout LL_Friend_Relative;
    /* access modifiers changed from: private */
    public LinearLayout LL_MySelf;
    /* access modifiers changed from: private */
    public LinearLayout LL_My_child;
    private String about = "";
    /* access modifiers changed from: private */
    public TextView my_child;
    String tutorFor = "Friend/Relative";
    /* access modifiers changed from: private */
    public TextView txt_frnd_relative;
    /* access modifiers changed from: private */
    public TextView txt_mySelf;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        setContentView((int) R.layout.activity_tutor_selection);
        this.LL_MySelf = (LinearLayout) findViewById(R.id.LL_MySelf);
        this.LL_My_child = (LinearLayout) findViewById(R.id.LL_My_child);
        this.LL_Friend_Relative = (LinearLayout) findViewById(R.id.LL_Friend_Relative);
        this.txt_mySelf = (TextView) findViewById(R.id.txt_mySelf);
        this.my_child = (TextView) findViewById(R.id.my_child);
        this.txt_frnd_relative = (TextView) findViewById(R.id.txt_frnd_relative);
        Intent intent = getIntent();
        if (intent != null) {
            this.Dob = intent.getStringExtra("DOB").toString();
            this.Gender = intent.getStringExtra("Gender").toString();
            this.about = intent.getStringExtra(PlaceFields.ABOUT).toString();
        }
        this.LL_MySelf.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TutorSelectionActivity.this.tutorFor = "My_Self";
                TutorSelectionActivity.this.LL_MySelf.setBackgroundResource(R.drawable.edit_bgbutton);
                TutorSelectionActivity.this.LL_My_child.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TutorSelectionActivity.this.LL_Friend_Relative.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TutorSelectionActivity.this.txt_mySelf.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.colorWhite));
                TutorSelectionActivity.this.my_child.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                TutorSelectionActivity.this.txt_frnd_relative.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
            }
        });
        this.LL_My_child.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TutorSelectionActivity.this.tutorFor = "My_child";
                TutorSelectionActivity.this.LL_MySelf.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TutorSelectionActivity.this.LL_My_child.setBackgroundResource(R.drawable.edit_bgbutton);
                TutorSelectionActivity.this.LL_Friend_Relative.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TutorSelectionActivity.this.txt_mySelf.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                TutorSelectionActivity.this.my_child.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.colorWhite));
                TutorSelectionActivity.this.txt_frnd_relative.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
            }
        });
        this.LL_Friend_Relative.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TutorSelectionActivity.this.tutorFor = "Friend/Relative";
                TutorSelectionActivity.this.LL_MySelf.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TutorSelectionActivity.this.LL_My_child.setBackgroundResource(R.drawable.edit_bgbutton_white);
                TutorSelectionActivity.this.LL_Friend_Relative.setBackgroundResource(R.drawable.edit_bgbutton);
                TutorSelectionActivity.this.txt_mySelf.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                TutorSelectionActivity.this.my_child.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                TutorSelectionActivity.this.txt_frnd_relative.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.colorWhite));
            }
        });
    }

    public void ContinueInit(View view) {
        Intent intent = new Intent(this, TakeClassesActivity.class);
        intent.putExtra("DOB", this.Dob);
        intent.putExtra("Gender", this.Gender);
        intent.putExtra(PlaceFields.ABOUT, this.about);
        intent.putExtra(PlaceFields.ABOUT, this.about);
        intent.putExtra("tutorFor", this.tutorFor);
        startActivity(intent);
        finish();
    }

    public void backWantTutor(View view) {
        onBackPressed();
        finish();
    }
}
