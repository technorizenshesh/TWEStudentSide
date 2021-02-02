package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.tech.twestudentside.R;
import com.tech.twestudentside.model.SliderPhotoItem;


import java.util.ArrayList;
import java.util.List;

public class SliderAdapterPhotos extends SliderViewAdapter<SliderAdapterPhotos.SliderAdapterVH> {


    private Context context;
    private List<SliderPhotoItem> sliderPhotoItems = new ArrayList<>();

    public SliderAdapterPhotos(Context context) {
        this.context = context;
    }

    public void renewItems(List<SliderPhotoItem> sliderPhotoItems) {
        this.sliderPhotoItems = sliderPhotoItems;
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterPhotos.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_photo, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterPhotos.SliderAdapterVH viewHolder, final int position) {

        SliderPhotoItem sliderPhotoItem = sliderPhotoItems.get(position);

        // viewHolder.textViewDescription.setText(sliderItem.getDescription());
        //viewHolder.textViewDescription.setTextSize(16);
        // viewHolder.textViewDescription.setTextColor(Color.WHITE);
        Glide.with(viewHolder.itemView)
                .load(sliderPhotoItem.getImageUrl())
                .fitCenter()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "This is item in position " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return sliderPhotoItems.size();
    }

     class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        ImageView imageGifContainer;
        TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            imageGifContainer = itemView.findViewById(R.id.iv_gif_container);
            //textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}
