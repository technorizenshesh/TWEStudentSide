package com.tech.twestudentside.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.model.BookingHistoryStatusModel;


import java.util.ArrayList;

/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class BookingHistoryRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    int pos = 0;
    private Button btn_no;
    private Button btn_yes;
    private Context mContext;
    private AlertDialog alertDialog;
    private ArrayList<BookingHistoryStatusModel> modelList;
    private OnItemClickListener mItemClickListener;
    private View promptsView;

    public BookingHistoryRecyclerViewAdapter(Context context, ArrayList<BookingHistoryStatusModel> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<BookingHistoryStatusModel> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itm_booking_history_status, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view
        if (holder instanceof ViewHolder) {
            final BookingHistoryStatusModel model = getItem(position);
            final ViewHolder genericViewHolder = (ViewHolder) holder;

           /* genericViewHolder.tutor_name.setText(model.getName());
            genericViewHolder.tutor_Old_year.setText(model.getName());
            genericViewHolder.tutor_distance.setText(model.getName());
            genericViewHolder.tutor_gender.setText(model.getName());
            genericViewHolder.txt_address.setText(model.getName());*/

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private BookingHistoryStatusModel getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, BookingHistoryStatusModel model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tutor_name;
        private TextView tutor_Old_year;
        private TextView tutor_distance;
        private TextView tutor_gender;
        private TextView txt_address;


        public ViewHolder(final View itemView) {
            super(itemView);

            this.tutor_name=itemView.findViewById(R.id.tutor_name);
            this.tutor_Old_year=itemView.findViewById(R.id.tutor_Old_year);
            this.tutor_distance=itemView.findViewById(R.id.tutor_distance);
            this.tutor_gender=itemView.findViewById(R.id.tutor_gender);
            this.txt_address=itemView.findViewById(R.id.txt_address);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }


}

