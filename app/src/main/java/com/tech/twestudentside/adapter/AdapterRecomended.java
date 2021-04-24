package com.tech.twestudentside.adapter;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.maps.model.LatLng;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.TrandingActivity;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.utils.RetrofitClients;
import java.util.ArrayList;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterRecomended extends RecyclerView.Adapter<AdapterRecomended.viewHolder> {
    String TotlaFullCoursePrice = "";
    ArrayList<home_model_data> arrayList;
    Context context;
    ImageView img_heart;
    boolean isLike = true;
    CircleImageView profile_image;

    public AdapterRecomended(FragmentActivity activity, ArrayList<home_model_data> arrayList2) {
        this.context = activity;
        this.arrayList = arrayList2;
    }

    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recomonded_item, viewGroup, false);

        this.img_heart = (ImageView) view.findViewById(R.id.img_heart);
        this.profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        return new viewHolder(view);
    }

    public void onBindViewHolder(viewHolder viewHolder2, final int position) {
        viewHolder2.txt_name.setText(this.arrayList.get(position).getTutorDetails().getUsername());
        viewHolder2.txt_gender.setText(this.arrayList.get(position).getGender());
        viewHolder2.txt_address.setText(this.arrayList.get(position).getLocation());
        String Dob = this.arrayList.get(position).getDob();
        String TutorProfile = this.arrayList.get(position).getProfileImage();
        String isFav = this.arrayList.get(position).getTutorDetails().getFavTutor();
        if (Dob != null && !Dob.equalsIgnoreCase("")) {
            String[] datrrr = Dob.split("-");
            String CalcuAge = getAge(Integer.parseInt(datrrr[2]), Integer.parseInt(datrrr[1]), Integer.parseInt(datrrr[0]));
            TextView textView = viewHolder2.txt_Dob;
            textView.setText(CalcuAge + " Years");
        }
        if (isFav.equalsIgnoreCase("NO")) {
            this.img_heart.setImageResource(R.drawable.heart);
        } else {
            this.img_heart.setImageResource(R.drawable.bahut_heart);
        }
        if (TutorProfile != null) {
            ((RequestBuilder) ((RequestBuilder) Glide.with(this.context).load(TutorProfile).circleCrop()).placeholder((int) R.drawable.home_banner3)).into((ImageView) this.profile_image);
        }
        if (!this.arrayList.get(position).getPerHourPayment().equalsIgnoreCase("")) {
            this.TotlaFullCoursePrice = "$" + this.arrayList.get(position).getTotalChargesIndividual();
            TextView textView2 = viewHolder2.txt_perHr_price;
            textView2.setText("$" + this.arrayList.get(position).getTotalChargesIndividual());
        }
        if (!this.arrayList.get(position).getDistance().equalsIgnoreCase("")) {
            viewHolder2.txt_distance.setText(this.arrayList.get(position).getTeachDistance());
        }
        this.img_heart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AdapterRecomended adapterRecomended = AdapterRecomended.this;
                adapterRecomended.addFavApi(adapterRecomended.arrayList.get(position).getUserId());
            }
        });
        viewHolder2.relative_item.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Preference.save(AdapterRecomended.this.context, Preference.KEY_Tutor_id, AdapterRecomended.this.arrayList.get(position).getUserId());
                Preference.save(AdapterRecomended.this.context, Preference.KEY_Tutor_price_indivisual, AdapterRecomended.this.arrayList.get(position).getPerHourPayment());
                Preference.save(AdapterRecomended.this.context, Preference.KEY_Tutor_price_grp, AdapterRecomended.this.arrayList.get(position).getPerHourPaymentGroup());
                AdapterRecomended.this.context.startActivity(new Intent(AdapterRecomended.this.context, TrandingActivity.class));
            }
        });
    }

    public int getItemCount() {
        return this.arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView iconName;
        RelativeLayout relative_item;
        TextView txt_Dob;
        TextView txt_address;
        TextView txt_distance;
        TextView txt_gender;
        TextView txt_name;
        TextView txt_perHr_price;

        public viewHolder(View itemView) {
            super(itemView);
            this.txt_Dob = (TextView) itemView.findViewById(R.id.txt_Dob);
            this.relative_item = (RelativeLayout) itemView.findViewById(R.id.relative_item);
            this.txt_name = (TextView) itemView.findViewById(R.id.txt_name);
            this.txt_gender = (TextView) itemView.findViewById(R.id.txt_gender);
            this.txt_address = (TextView) itemView.findViewById(R.id.txt_address);
            this.icon = (ImageView) itemView.findViewById(R.id.icon);
            this.txt_perHr_price = (TextView) itemView.findViewById(R.id.txt_perHr_price);
            this.txt_distance = (TextView) itemView.findViewById(R.id.txt_distance);
        }
    }

    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        Location locationA = new Location(ExifInterface.GPS_MEASUREMENT_IN_PROGRESS);
        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);
        return (double) locationA.distanceTo(locationB);
    }

    /* access modifiers changed from: private */
    public void addFavApi(String tutor_id) {
        RetrofitClients.getInstance().getApi().add_fav(Preference.get(this.context, Preference.KEY_USER_ID), tutor_id).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    if (status.equalsIgnoreCase("1")) {
                        if (message.equalsIgnoreCase("successfull")) {
                            AdapterRecomended.this.img_heart.setImageResource(R.drawable.bahut_heart);
                        } else {
                            AdapterRecomended.this.img_heart.setImageResource(R.drawable.heart);
                        }
                        Toast.makeText(AdapterRecomended.this.context, message, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(AdapterRecomended.this.context, message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(AdapterRecomended.this.context, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private String getAge(int year, int month, int day) {
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();
        dob.set(year, month, day);
        int age = today.get(1) - dob.get(1);
        if (today.get(6) < dob.get(6)) {
            age--;
        }
        return new Integer(age).toString();
    }
}
