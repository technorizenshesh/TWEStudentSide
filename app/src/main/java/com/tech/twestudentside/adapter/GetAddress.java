package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.model.getShiipingAddressData;
import com.tech.twestudentside.utils.SessionManager;
import java.util.ArrayList;

public class GetAddress extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static CheckBox lastChecked = null;
    private static int lastCheckedPos = 0;
    private AlertDialog alertDialog;
    private Button btn_no;
    private Button btn_yes;
    Fragment fragment;
    boolean isLike = true;
    int lastPosition = 0;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<getShiipingAddressData> modelList;
    private View promptsView;
    private SessionManager sessionManager;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, getShiipingAddressData getshiipingaddressdata);
    }

    public GetAddress(Context context, ArrayList<getShiipingAddressData> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<getShiipingAddressData> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_shippping_address, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final getShiipingAddressData model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txt_address.setText(model.getAddress());
            genericViewHolder.RR_select.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Preference.save(GetAddress.this.mContext, Preference.KEY_location_id, model.getId());
                    Preference.save(GetAddress.this.mContext, Preference.KEY_location_name, model.getAddress());
                    GetAddress.this.lastPosition = position;
                    GetAddress.this.notifyDataSetChanged();
                }
            });
            if (this.lastPosition == position) {
                Preference.save(this.mContext, Preference.KEY_location_name, model.getAddress());
                Preference.save(this.mContext, Preference.key_Location_id, model.getId());
                genericViewHolder.checkBox.setChecked(true);
                return;
            }
            genericViewHolder.checkBox.setChecked(false);
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private getShiipingAddressData getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public CardView RR_select;
        /* access modifiers changed from: private */
        public CheckBox checkBox;
        /* access modifiers changed from: private */
        public TextView txt_address;
        private TextView txt_address_type;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.txt_address = (TextView) itemView.findViewById(R.id.txt_address);
            this.txt_address_type = (TextView) itemView.findViewById(R.id.txt_address_type);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.img_check);
            this.RR_select = (CardView) itemView.findViewById(R.id.RR_select);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GetAddress.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (getShiipingAddressData) GetAddress.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
