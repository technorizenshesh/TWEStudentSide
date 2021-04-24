package com.tech.twestudentside.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import com.tech.twestudentside.R;
import com.tech.twestudentside.databinding.ActivityFillterBinding;


public class FillterActivity extends AppCompatActivity {
    ActivityFillterBinding binding;
    ImageView iv_back;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(1280);
        }
        this.binding =DataBindingUtil.setContentView(this, R.layout.activity_fillter);
        init();
    }

    private void init() {
        this.binding.ivBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FillterActivity.this.onBackPressed();
            }
        });
        this.binding.txt1TabBackground.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$0$FillterActivity(view);
            }
        });
        this.binding.txt3TabStudents.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$1$FillterActivity(view);
            }
        });
        this.binding.txtOnline.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$2$FillterActivity(view);
            }
        });
        this.binding.txtTeacher.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$3$FillterActivity(view);
            }
        });
        this.binding.txtStudentHome.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$4$FillterActivity(view);
            }
        });
        this.binding.txtMale.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$5$FillterActivity(view);
            }
        });
        this.binding.txtFemale.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$6$FillterActivity(view);
            }
        });
        this.binding.txtChild.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$7$FillterActivity(view);
            }
        });
        this.binding.txtTeen.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$8$FillterActivity(view);
            }
        });
        this.binding.txtAdult.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$9$FillterActivity(view);
            }
        });
        this.binding.txtSenior.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                FillterActivity.this.lambda$init$10$FillterActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$init$0$FillterActivity(View v) {
        this.binding.txt1TabBackground.setBackgroundResource(R.drawable.color_pink);
        this.binding.txt3TabStudents.setBackgroundResource(R.drawable.color_gray);
        this.binding.txt3TabStudents.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txt1TabBackground.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    public /* synthetic */ void lambda$init$1$FillterActivity(View v) {
        this.binding.txt1TabBackground.setBackgroundResource(R.drawable.color_gray);
        this.binding.txt3TabStudents.setBackgroundResource(R.drawable.color_pink);
        this.binding.txt1TabBackground.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txt3TabStudents.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    public /* synthetic */ void lambda$init$2$FillterActivity(View v) {
        this.binding.txtOnline.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtTeacher.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtStudentHome.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtTeacher.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtStudentHome.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtOnline.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    public /* synthetic */ void lambda$init$3$FillterActivity(View v) {
        this.binding.txtOnline.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtTeacher.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtStudentHome.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtOnline.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtStudentHome.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtTeacher.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    public /* synthetic */ void lambda$init$4$FillterActivity(View v) {
        this.binding.txtOnline.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtTeacher.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtStudentHome.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtOnline.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtTeacher.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtStudentHome.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    public /* synthetic */ void lambda$init$5$FillterActivity(View v) {
        this.binding.txtFemale.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtMale.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtMale.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        this.binding.txtFemale.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
    }

    public /* synthetic */ void lambda$init$6$FillterActivity(View v) {
        this.binding.txtMale.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtFemale.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtMale.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtFemale.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }

    public /* synthetic */ void lambda$init$7$FillterActivity(View v) {
        this.binding.txtChild.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtTeen.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtAdult.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtSenior.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtChild.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        this.binding.txtTeen.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtAdult.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtSenior.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
    }

    public /* synthetic */ void lambda$init$8$FillterActivity(View v) {
        this.binding.txtChild.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtTeen.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtAdult.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtSenior.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtChild.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtTeen.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        this.binding.txtAdult.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtSenior.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
    }

    public /* synthetic */ void lambda$init$9$FillterActivity(View v) {
        this.binding.txtChild.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtTeen.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtAdult.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtSenior.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtChild.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtTeen.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtAdult.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
        this.binding.txtSenior.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
    }

    public /* synthetic */ void lambda$init$10$FillterActivity(View v) {
        this.binding.txtChild.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtTeen.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtAdult.setBackgroundResource(R.drawable.color_gray);
        this.binding.txtSenior.setBackgroundResource(R.drawable.color_pink);
        this.binding.txtChild.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtTeen.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtAdult.setTextColor(ContextCompat.getColor(this, R.color.colorBlack));
        this.binding.txtSenior.setTextColor(ContextCompat.getColor(this, R.color.colorWhite));
    }
}
