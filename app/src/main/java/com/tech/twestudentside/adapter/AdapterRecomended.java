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

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.maps.model.LatLng;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.PortFoliyoActivity;
import com.tech.twestudentside.activity.TrandingActivity;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.model.itemRecomendedModel;
import com.tech.twestudentside.utils.RetrofitClients;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterRecomended extends RecyclerView.Adapter<AdapterRecomended.viewHolder>  {

    Context context;
    ArrayList<home_model_data> arrayList;
    boolean isLike=true;
    ImageView img_heart;
    public AdapterRecomended(FragmentActivity activity, ArrayList<home_model_data> arrayList) {
        this.context=activity;
        this.arrayList = arrayList;

    }

    @NonNull
    @Override
    public  AdapterRecomended.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recomonded_item, viewGroup, false);
        img_heart = (ImageView) view.findViewById(R.id.img_heart);
        return new viewHolder(view);
    }
    @Override
    public  void onBindViewHolder(AdapterRecomended.viewHolder viewHolder, final int position) {

        viewHolder.txt_name.setText(arrayList.get(position).getTutorDetails().getUsername());
        viewHolder.txt_gender.setText(arrayList.get(position).getGender());
        viewHolder.txt_address.setText(arrayList.get(position).getLocation());

        String Dob = arrayList.get(position).getDob();
        String isFav = arrayList.get(position).getTutorDetails().getFavTutor();

        if(!Dob.equalsIgnoreCase(""))
        {
            viewHolder.txt_Dob.setText(getAge(Dob)+" years");
        }

        if(isFav.equalsIgnoreCase("NO"))
        {
            img_heart.setImageResource(R.drawable.heart);

        }else
        {
            img_heart.setImageResource(R.drawable.bahut_heart);

        }


        if(!arrayList.get(position).getPerHourPayment().equalsIgnoreCase(""))
        {
            viewHolder.txt_perHr_price.setText("$"+arrayList.get(position).getTotalChargesIndividual());
        }

        if(!arrayList.get(position).getDistance().equalsIgnoreCase(""))
        {
            viewHolder.txt_distance.setText(arrayList.get(position).getDistance()+"Km away");
        }



        img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addFavApi(arrayList.get(position).getUserId());
            }
        });




        // viewHolder.icon.setImageResource(arrayList.get(position).getImage());

        viewHolder.relative_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Preference.save(context,Preference.KEY_Tutor_id,arrayList.get(position).getUserId());

                Preference.save(context,Preference.KEY_Tutor_price_indivisual,arrayList.get(position).getPerHourPayment());
                Preference.save(context,Preference.KEY_Tutor_price_grp,arrayList.get(position).getPerHourPaymentGroup());


                Intent intent= new Intent(context, TrandingActivity.class);
                context.startActivity(intent);

             /*   switch (position){

                    case 0:
                        Intent intent= new Intent(context, TrandingActivity.class);
                        context.startActivity(intent);
                        break;
                    case 1:
                        Intent intent1= new Intent(context, TrandingActivity.class);
                        context.startActivity(intent1);
                        Toast.makeText(view.getContext(),"click on item: "+position,Toast.LENGTH_LONG).show();
                        break;

                    case 2:
                        Intent intent2= new Intent(context, TrandingActivity.class);
                        context.startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3= new Intent(context,TrandingActivity.class);
                        context.startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4= new Intent(context, TrandingActivity.class);
                        context.startActivity(intent4);
                        break;


                }*/

            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        ImageView icon;
        TextView iconName;
        RelativeLayout relative_item;
        TextView txt_name;
        TextView txt_gender;
        TextView txt_address;
        TextView txt_perHr_price;
        TextView txt_Dob;
        TextView txt_distance;

        public viewHolder(View itemView) {
            super(itemView);
            txt_Dob=itemView.findViewById(R.id.txt_Dob);
            relative_item=itemView.findViewById(R.id.relative_item);
            txt_name=itemView.findViewById(R.id.txt_name);
            txt_gender=itemView.findViewById(R.id.txt_gender);
            txt_address=itemView.findViewById(R.id.txt_address);
            icon = (ImageView) itemView.findViewById(R.id.icon);

            txt_perHr_price = (TextView) itemView.findViewById(R.id.txt_perHr_price);
            txt_distance = (TextView) itemView.findViewById(R.id.txt_distance);
            //iconName = (TextView) itemView.findViewById(R.id.icon_name);

        }
    }


    public int getAge (int _year, int _month, int _day) {

        GregorianCalendar cal = new GregorianCalendar();
        int y, m, d, a;

        y = cal.get(Calendar.YEAR);
        m = cal.get(Calendar.MONTH);
        d = cal.get(Calendar.DAY_OF_MONTH);
        cal.set(_year, _month, _day);
        a = y - cal.get(Calendar.YEAR);
        if ((m < cal.get(Calendar.MONTH))
                || ((m == cal.get(Calendar.MONTH)) && (d < cal
                .get(Calendar.DAY_OF_MONTH)))) {
            --a;
        }
        if(a < 0)
            throw new IllegalArgumentException("Age < 0");
        return a;
    }


    public double getDistance(LatLng LatLng1, LatLng LatLng2) {
        double distance = 0;
        Location locationA = new Location("A");
        locationA.setLatitude(LatLng1.latitude);
        locationA.setLongitude(LatLng1.longitude);
        Location locationB = new Location("B");
        locationB.setLatitude(LatLng2.latitude);
        locationB.setLongitude(LatLng2.longitude);
        distance = locationA.distanceTo(locationB);
        return distance;
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

        String UserId =   Preference.get(context,Preference.KEY_USER_ID);

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

                        if(message.equalsIgnoreCase("successfull"))
                        {

                            img_heart.setImageResource(R.drawable.bahut_heart);

                        }else
                        {
                            img_heart.setImageResource(R.drawable.heart);

                        }
                        // progressBar.setVisibility(View.GONE);
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        // progressBar.setVisibility(View.GONE);
                    }

                } catch (Exception e) {

                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}

