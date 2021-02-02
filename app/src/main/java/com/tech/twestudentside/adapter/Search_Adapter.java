package com.tech.twestudentside.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.FavouriteFragment;
import com.tech.twestudentside.model.GetFavModelOne;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.utils.RetrofitClients;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A custom adapter to use with the RecyclerView widget.
 */
public class Search_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<home_model_data> modelList;
    private Search_Adapter.OnItemClickListener mItemClickListener;

    public Search_Adapter(Context context, ArrayList<home_model_data> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<home_model_data> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public Search_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search, viewGroup, false);

        return new Search_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view

        if (holder instanceof Search_Adapter.ViewHolder) {

            final home_model_data model = getItem(position);

            final Search_Adapter.ViewHolder genericViewHolder = (Search_Adapter.ViewHolder) holder;

             genericViewHolder.txt_tutorName.setText(model.getTutorDetails().getUsername());
             genericViewHolder.txt_gender.setText(model.getGender());
           //  genericViewHolder.txt_price.setText(model.getTotalChargesIndividual());
             genericViewHolder.txt_distance.setText(model.getDistance());


        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final Search_Adapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private home_model_data getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, home_model_data model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_tutorName;
        private TextView txt_Dob;
        private TextView txt_gender;
        private TextView txt_price;
        private TextView txt_distance;


        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_tutorName = itemView.findViewById(R.id.txt_tutorName);
            this.txt_Dob = itemView.findViewById(R.id.txt_Dob);
            this.txt_gender = itemView.findViewById(R.id.txt_gender);
            this.txt_price = itemView.findViewById(R.id.txt_price);
            this.txt_distance = itemView.findViewById(R.id.txt_distance);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });
        }
    }


}

