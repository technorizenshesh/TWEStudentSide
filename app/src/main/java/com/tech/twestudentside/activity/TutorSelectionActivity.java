package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tech.twestudentside.R;

public class TutorSelectionActivity extends AppCompatActivity {

    private LinearLayout LL_MySelf;
    private LinearLayout LL_My_child;
    private LinearLayout LL_Friend_Relative;

    private TextView txt_mySelf;
    private TextView my_child;
    private TextView txt_frnd_relative;

    String tutorFor= "Friend/Relative";

   private String Dob="";
    private  String Gender="";
    private String about="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);}
        setContentView(R.layout.activity_tutor_selection);

        LL_MySelf=findViewById(R.id.LL_MySelf);
        LL_My_child=findViewById(R.id.LL_My_child);
        LL_Friend_Relative=findViewById(R.id.LL_Friend_Relative);

        txt_mySelf=findViewById(R.id.txt_mySelf);
        my_child=findViewById(R.id.my_child);
        txt_frnd_relative=findViewById(R.id.txt_frnd_relative);

        Intent intent=getIntent();
        if(intent != null)
        {
             Dob = intent.getStringExtra("DOB").toString();
             Gender = intent.getStringExtra("Gender").toString();
             about = intent.getStringExtra("about").toString();
        }

        LL_MySelf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tutorFor ="My_Self";

                LL_MySelf.setBackgroundResource(R.drawable.edit_bgbutton);
                LL_My_child.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_Friend_Relative.setBackgroundResource(R.drawable.edit_bgbutton_white);

                txt_mySelf.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.colorWhite));
                my_child.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                txt_frnd_relative.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));

            }
        });

        LL_My_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tutorFor ="My_child";

                LL_MySelf.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_My_child.setBackgroundResource(R.drawable.edit_bgbutton);
                LL_Friend_Relative.setBackgroundResource(R.drawable.edit_bgbutton_white);

                txt_mySelf.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                my_child.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.colorWhite));
                txt_frnd_relative.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));


            }
        });

        LL_Friend_Relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tutorFor ="Friend/Relative";

                LL_MySelf.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_My_child.setBackgroundResource(R.drawable.edit_bgbutton_white);
                LL_Friend_Relative.setBackgroundResource(R.drawable.edit_bgbutton);

                txt_mySelf.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                my_child.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.color_txtBlue));
                txt_frnd_relative.setTextColor(ContextCompat.getColor(TutorSelectionActivity.this, R.color.colorWhite));


            }
        });

    }

    public void ContinueInit(View view) {
        Intent intent= new Intent(TutorSelectionActivity.this, TakeClassesActivity.class);
        intent.putExtra("DOB",Dob);
        intent.putExtra("Gender",Gender);
        intent.putExtra("about",about);
        intent.putExtra("about",about);
        intent.putExtra("tutorFor",tutorFor);
        startActivity(intent);
        finish();
    }

    public void backWantTutor(View view) {
        onBackPressed();
        finish();
    }
}