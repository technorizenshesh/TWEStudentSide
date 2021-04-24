package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.tech.twestudentside.R;
import com.tech.twestudentside.model.TutorCategoryModel;
import java.util.ArrayList;

public class tutor_category_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<TutorCategoryModel> modelList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, TutorCategoryModel tutorCategoryModel);
    }

    public tutor_category_Adapter(Context context, ArrayList<TutorCategoryModel> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<TutorCategoryModel> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tutor_category, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder genericViewHolder = (ViewHolder) holder;
            String image_URL = getItem(position).getImage().toString();
            if (image_URL != null) {
                ((RequestBuilder) Glide.with(this.mContext).load(image_URL).placeholder(R.drawable.aa)).into(genericViewHolder.img_category);
            }
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private TutorCategoryModel getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView img_category;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.img_category = (ImageView) itemView.findViewById(R.id.img_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    tutor_category_Adapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (TutorCategoryModel) tutor_category_Adapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
