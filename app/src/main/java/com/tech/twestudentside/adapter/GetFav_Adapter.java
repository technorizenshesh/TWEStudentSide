package com.tech.twestudentside.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.FavouriteFragment;
import com.tech.twestudentside.model.GetFavModelOne;
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
public class GetFav_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private Fragment fragment;
    private ArrayList<GetFavModelOne> modelList;
    private GetFav_Adapter.OnItemClickListener mItemClickListener;

    public GetFav_Adapter(Context context, ArrayList<GetFavModelOne> modelList,Fragment fragment) {
        this.mContext = context;
        this.modelList = modelList;
        this.fragment = fragment;
    }

    public void updateList(ArrayList<GetFavModelOne> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();
    }

    @Override
    public GetFav_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_get_fav, viewGroup, false);

        return new GetFav_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //Here you can fill your row view

        if (holder instanceof GetFav_Adapter.ViewHolder) {

            final GetFavModelOne model = getItem(position);

            final GetFav_Adapter.ViewHolder genericViewHolder = (GetFav_Adapter.ViewHolder) holder;

           genericViewHolder.txt_tutorName.setText(model.getTutorDetails().getUsername());
           genericViewHolder.txt_price.setText("$"+model.getDetails().getPerHourPayment());
           genericViewHolder.txt_gender.setText(model.getDetails().getGender());

         //  String DobTutor =model.getDetails().getDob();

//           genericViewHolder.txt_Dob.setText(getAge(DobTutor)+" years");

           genericViewHolder.img_like.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   modelList.remove(position);
                   addFavApi(model.getTutorId());

               }
           });


        }
    }

    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final GetFav_Adapter.OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private GetFavModelOne getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {

        void onItemClick(View view, int position, GetFavModelOne model);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_tutorName;
        private TextView txt_Dob;
        private TextView txt_distance;
        private TextView txt_gender;
        private TextView txt_price;
        private ImageView img_like;

        public ViewHolder(final View itemView) {
            super(itemView);

            this.txt_tutorName = itemView.findViewById(R.id.txt_tutorName);
            this.txt_Dob = itemView.findViewById(R.id.txt_Dob);
            this.txt_distance = itemView.findViewById(R.id.txt_distance);
            this.txt_gender = itemView.findViewById(R.id.txt_gender);
            this.txt_price = itemView.findViewById(R.id.txt_price);
            this.img_like = itemView.findViewById(R.id.img_like);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));
                }
            });
        }
    }

    private int getAge(String dobString){

        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            date = sdf.parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(date == null) return 0;

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.setTime(date);

        int year = dob.get(Calendar.YEAR);
        int month = dob.get(Calendar.MONTH);
        int day = dob.get(Calendar.DAY_OF_MONTH);

        dob.set(year, month+1, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }
        return age;
    }

    private void addFavApi(String tutor_id) {

        String UserId =   Preference.get(mContext,Preference.KEY_USER_ID);

        Call<ResponseBody> call = RetrofitClients
                .getInstance()
                .getApi()
                .add_fav(UserId,tutor_id);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status   = jsonObject.getString ("status");
                    String message = jsonObject.getString("message");

                    if (status.equalsIgnoreCase("1")) {

                        // progressBar.setVisibility(View.GONE);

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();

                        ((FavouriteFragment)fragment).getFavApi();

                    } else {

                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                        // progressBar.setVisibility(View.GONE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

