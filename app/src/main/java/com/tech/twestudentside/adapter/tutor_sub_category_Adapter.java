package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.model.TutorSubDataCategory;
import java.util.ArrayList;

public class tutor_sub_category_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<TutorSubDataCategory> modelList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, TutorSubDataCategory tutorSubDataCategory);
    }

    public tutor_sub_category_Adapter(Context context, ArrayList<TutorSubDataCategory> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<TutorSubDataCategory> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tutor_sub_category, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).check_box_category.setText(getItem(position).getCategoryName());
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private TutorSubDataCategory getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView check_box_category;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.check_box_category = (TextView) itemView.findViewById(R.id.check_box_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    tutor_sub_category_Adapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (TutorSubDataCategory) tutor_sub_category_Adapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
