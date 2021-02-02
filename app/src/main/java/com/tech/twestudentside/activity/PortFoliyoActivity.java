package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.SliderAdapterPhotos;
import com.tech.twestudentside.model.SliderPhotoItem;

import java.util.ArrayList;
import java.util.List;

public class PortFoliyoActivity extends AppCompatActivity {


    SliderView sliderView1;
    private SliderAdapterPhotos adapterPhotos;
    ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_port_foliyo);
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }





}