package com.tech.twestudentside.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.tech.twestudentside.R;

public class VideoCallingActivity extends AppCompatActivity {
    ImageView iv_back;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_calling);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                VideoCallingActivity.this.onBackPressed();
            }
        });
    }

    public void cancelCallInit(View view) {
        onBackPressed();
    }
}
