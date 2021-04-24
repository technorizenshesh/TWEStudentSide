package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.model.pricelistModel;
import java.util.ArrayList;

public class PriceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: c */
    String f446c = "";
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<pricelistModel> modelList;
    int pos = 0;
    int row_index = 0;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, pricelistModel pricelistmodel);
    }

    public PriceAdapter(Context context, ArrayList<pricelistModel> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<pricelistModel> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.price_item, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            pricelistModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txt_time.setText(model.getDay());
            TextView access$100 = genericViewHolder.txt_discount;
            access$100.setText("saving  " + model.getDiscount());
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private pricelistModel getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public TextView txt_discount;
        /* access modifiers changed from: private */
        public TextView txt_time;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.txt_time = (TextView) itemView.findViewById(R.id.txt_time);
            this.txt_discount = (TextView) itemView.findViewById(R.id.txt_discount);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    PriceAdapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (pricelistModel) PriceAdapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
