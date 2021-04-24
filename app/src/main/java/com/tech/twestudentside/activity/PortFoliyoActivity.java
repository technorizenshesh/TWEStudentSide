package com.tech.twestudentside.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.smarteist.autoimageslider.SliderView;
import com.tech.twestudentside.R;

public class PortFoliyoActivity extends AppCompatActivity {
    //private SliderAdapterPhotos adapterPhotos;
    ImageView iv_back;
    SliderView sliderView1;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_foliyo);
        ImageView imageView = (ImageView) findViewById(R.id.iv_back);
        this.iv_back = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PortFoliyoActivity.this.onBackPressed();
            }
        });
    }
}
