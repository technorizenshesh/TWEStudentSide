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
import com.facebook.share.internal.ShareConstants;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.FavouriteFragment;
import com.tech.twestudentside.model.GetFavModelOne;
import com.tech.twestudentside.utils.RetrofitClients;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFav_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /* access modifiers changed from: private */
    public Fragment fragment;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<GetFavModelOne> modelList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, GetFavModelOne getFavModelOne);
    }

    public GetFav_Adapter(Context context, ArrayList<GetFavModelOne> modelList2, Fragment fragment2) {
        this.mContext = context;
        this.modelList = modelList2;
        this.fragment = fragment2;
    }

    public void updateList(ArrayList<GetFavModelOne> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_get_fav, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            final GetFavModelOne model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            String Name = model.getTutorDetails().getUsername();
            if (!Name.equalsIgnoreCase("")) {
                genericViewHolder.txt_tutorName.setText(Name);
            }
            String Price = model.getDetails().getPerHourPayment();
            if (!Price.equalsIgnoreCase("")) {
                TextView access$100 = genericViewHolder.txt_price;
                access$100.setText("$" + Price);
            }
            genericViewHolder.txt_gender.setText(model.getDetails().getGender());
            genericViewHolder.img_like.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    GetFav_Adapter.this.modelList.remove(position);
                    GetFav_Adapter.this.addFavApi(model.getTutorId());
                }
            });
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private GetFavModelOne getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public ImageView img_like;
        private TextView txt_Dob;
        private TextView txt_distance;
        /* access modifiers changed from: private */
        public TextView txt_gender;
        /* access modifiers changed from: private */
        public TextView txt_price;
        /* access modifiers changed from: private */
        public TextView txt_tutorName;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.txt_tutorName = (TextView) itemView.findViewById(R.id.txt_tutorName);
            this.txt_Dob = (TextView) itemView.findViewById(R.id.txt_Dob);
            this.txt_distance = (TextView) itemView.findViewById(R.id.txt_distance);
            this.txt_gender = (TextView) itemView.findViewById(R.id.txt_gender);
            this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            this.img_like = (ImageView) itemView.findViewById(R.id.img_like);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    GetFav_Adapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (GetFavModelOne) GetFav_Adapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }

    private int getAge(String dobString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dobString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        }
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.setTime(date);
        dob.set(dob.get(1), dob.get(2) + 1, dob.get(5));
        int age = today.get(1) - dob.get(1);
        if (today.get(6) < dob.get(6)) {
            return age - 1;
        }
        return age;
    }

    /* access modifiers changed from: private */
    public void addFavApi(String tutor_id) {
        RetrofitClients.getInstance().getApi().add_fav(Preference.get(this.mContext, Preference.KEY_USER_ID), tutor_id).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    if (status.equalsIgnoreCase("1")) {
                        Toast.makeText(GetFav_Adapter.this.mContext, message, 0).show();
                        ((FavouriteFragment) GetFav_Adapter.this.fragment).getFavApi();
                        return;
                    }
                    Toast.makeText(GetFav_Adapter.this.mContext, message, 0).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(GetFav_Adapter.this.mContext, t.getMessage(), 0).show();
            }
        });
    }
}
