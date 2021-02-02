package com.tech.twestudentside.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.tech.twestudentside.R;
import com.tech.twestudentside.model.RelatedPropertyModel;

import java.util.ArrayList;

public class AdapterRelatedProperty extends RecyclerView.Adapter<AdapterRelatedProperty.viewHolder>  {



    Context context;
    ArrayList<RelatedPropertyModel> arrayList;



    public AdapterRelatedProperty(FragmentActivity activity, ArrayList<RelatedPropertyModel> arrayList) {
        this.context=activity;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public  AdapterRelatedProperty.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recomonded_item, viewGroup, false);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(AdapterRelatedProperty.viewHolder viewHolder, int position) {
        //viewHolder.iconName.setText(arrayList.get(position).getName());
       // viewHolder.icon.setImageResource(arrayList.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView iconName;

        public viewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.icon);
           // iconName = (TextView) itemView.findViewById(R.id.icon_name);

        }
    }
}
