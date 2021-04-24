package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.model.RelatedModelData;
import java.util.ArrayList;

public class AdapterRelatedProperty extends RecyclerView.Adapter<AdapterRelatedProperty.viewHolder> {
    ArrayList<RelatedModelData> arrayList;
    Context context;

    public AdapterRelatedProperty(FragmentActivity activity, ArrayList<RelatedModelData> arrayList2) {
        this.context = activity;
        this.arrayList = arrayList2;
    }

    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new viewHolder(LayoutInflater.from(this.context).inflate(R.layout.recomonded_item, viewGroup, false));
    }

    public void onBindViewHolder(viewHolder viewHolder2, int position) {
    }

    public int getItemCount() {
        return this.arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView iconName;

        public viewHolder(View itemView) {
            super(itemView);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
