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
import com.tech.twestudentside.model.pricelistModel;

import java.util.ArrayList;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class PriceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    int pos = 0;
    private Context mContext;
    private ArrayList<pricelistModel> modelList;
    private OnItemClickListener mItemClickListener;
    String c="";
    int row_index=0;
    public PriceAdapter(Context context, ArrayList<pricelistModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<pricelistModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.price_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {

            final pricelistModel model = getItem(position);

            final ViewHolder genericViewHolder = (ViewHolder) holder;

            genericViewHolder.txt_time.setText(model.getDay());
            genericViewHolder.txt_discount.setText("saving  "+model.getDiscount());

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

    private pricelistModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, pricelistModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        private TextView txt_time;
        private TextView txt_discount;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_time=itemView.findViewById(R.id.txt_time);
            this.txt_discount=itemView.findViewById(R.id.txt_discount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }



}

