package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.model.DaysModel;
import java.util.ArrayList;

public class DaySelectTimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: c */
    String f445c = "";
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<DaysModel> modelList;
    int pos = 0;
    int row_index = 0;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, DaysModel daysModel);
    }

    public DaySelectTimeAdapter(Context context, ArrayList<DaysModel> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<DaysModel> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.open_day, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            DaysModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txt_day.setText(model.getDay());
            genericViewHolder.txt_day.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DaySelectTimeAdapter.this.row_index = position;
                    DaySelectTimeAdapter.this.notifyDataSetChanged();
                }
            });
            if (this.row_index == position) {
                model.setSelected(true);
                genericViewHolder.txt_day.setTextColor(ContextCompat.getColor(this.mContext, R.color.colorWhite));
                genericViewHolder.txt_day.setBackgroundResource(R.drawable.color_pink);
                return;
            }
            model.setSelected(false);
            genericViewHolder.txt_day.setTextColor(ContextCompat.getColor(this.mContext, R.color.colorBlack));
            genericViewHolder.txt_day.setBackgroundResource(R.drawable.color_gray);
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private DaysModel getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView txt_day;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.txt_day = (TextView) itemView.findViewById(R.id.txt_day);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DaySelectTimeAdapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (DaysModel) DaySelectTimeAdapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
