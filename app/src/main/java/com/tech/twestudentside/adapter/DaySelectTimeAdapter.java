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

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class DaySelectTimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    int pos = 0;
    private Context mContext;
    private ArrayList<DaysModel> modelList;
    private OnItemClickListener mItemClickListener;
    String c="";
    int row_index=0;
    public DaySelectTimeAdapter(Context context, ArrayList<DaysModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<DaysModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.open_day, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {

            final DaysModel model = getItem(position);

            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_day.setText(model.getDay());

            genericViewHolder.txt_day.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    row_index=position;

                    notifyDataSetChanged();

                }
            });

            if(row_index==position){

                model.setSelected(true);

                genericViewHolder.txt_day.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));
                genericViewHolder.txt_day.setBackgroundResource(R.drawable.color_pink);

            }
            else
            {
                model.setSelected(false);

                genericViewHolder.txt_day.setTextColor(ContextCompat.getColor(mContext, R.color.colorBlack));
                genericViewHolder.txt_day.setBackgroundResource(R.drawable.color_gray);
            }



           // genericViewHolder.txt_time_one.setText(model.getTie_one());
        }
    }


    @Override
    public int getItemCount() {
        return modelList.size();
    }

   public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private DaysModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, DaysModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_day;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_day=itemView.findViewById(R.id.txt_day);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }



}

