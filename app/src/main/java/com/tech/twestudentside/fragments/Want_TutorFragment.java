package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.LoginActivity;
import com.tech.twestudentside.activity.MapActivity;
import com.tech.twestudentside.activity.TrandingActivity;
import com.tech.twestudentside.adapter.AdapterRecomended;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.TutorCategoryModel;
import com.tech.twestudentside.model.TutorCategory_Model;
import com.tech.twestudentside.model.home_model;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.model.itemRecomendedModel;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Want_TutorFragment extends Fragment {

    FragmentListener listener;

    LinearLayout myPropertyId,gridViewId,mapViewId;

    ArrayList<home_model_data> arrayList;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView txt_empty_tutor;
    int[] icons = {R.drawable.penthouse,R.drawable.penthouse,R.drawable.penthouse,
            R.drawable.penthouse,R.drawable.penthouse,R.drawable.penthouse};
    //String[] iconsName = {"Villas,Township,Penthouse,Compound,Office,Plots"};
    private SessionManager sessionManager;
    private String android_id;

    public Want_TutorFragment(FragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_want__tutor, container, false);

        sessionManager = new SessionManager(getActivity());

        gridViewId = view.findViewById(R.id.grid_ViewId);
        txt_empty_tutor = view.findViewById(R.id.txt_empty_tutor);
        progressBar = view.findViewById(R.id.progressBar);
        gridViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gridViewId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
                mapViewId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                listener.click(new Want_TutorFragment(listener));
            }
        });
        mapViewId = view.findViewById(R.id.map_ViewId);
        mapViewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gridViewId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_gray));
                mapViewId.setBackgroundDrawable(getResources().getDrawable(R.drawable.color_pink));
                listener.click(new MapFragment(listener));
            }
        });


        recyclerView = view.findViewById(R.id.recycler_view);
        arrayList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,
                false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

       /* for (int i = 0; i < icons.length-1; i++) {
            itemRecomendedModel itemRecomendedModel = new itemRecomendedModel();
            itemRecomendedModel.setImage(icons[i]);
            //itemModel.setName(iconsName[i]);
            //add in array list
            arrayList.add(itemRecomendedModel);
        }*/

        if (sessionManager.isNetworkAvailable()) {

            progressBar.setVisibility(View.VISIBLE);

            getHome();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }


        myPropertyId=view.findViewById(R.id.myPropertyId);
        myPropertyId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), TrandingActivity.class));
            }
        });

        return view;
    }



    private void getHome() {

      String UserId =  sessionManager.getUserID();
      String type =  Preference.get(getActivity(),Preference.key_Type);
        Call<home_model> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_tutor(type,UserId);
        call.enqueue(new Callback<home_model>() {
            @Override
            public void onResponse(Call<home_model> call, Response<home_model> response) {

                try {

                    home_model finallyPr = response.body();

                    progressBar.setVisibility(View.GONE);

                    //JSONObject jsonObject = new JSONObject(response.body().string());

                    String status   = finallyPr.getStatus ();
                    String message = finallyPr.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                           arrayList = (ArrayList<home_model_data>) finallyPr.getResult();

                        AdapterRecomended adapterRecomended = new AdapterRecomended(getActivity(), arrayList);
                        recyclerView.setAdapter(adapterRecomended);

                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    } else {
                        progressBar.setVisibility(View.GONE);
                        txt_empty_tutor.setVisibility(View.VISIBLE);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    txt_empty_tutor.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<home_model> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                txt_empty_tutor.setVisibility(View.VISIBLE);
            }
        });

    }



}