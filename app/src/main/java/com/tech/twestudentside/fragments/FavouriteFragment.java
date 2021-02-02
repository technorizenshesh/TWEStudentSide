package com.tech.twestudentside.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.AdapterRecomended;
import com.tech.twestudentside.adapter.GetFav_Adapter;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.GetFavModel;
import com.tech.twestudentside.model.GetFavModelOne;
import com.tech.twestudentside.model.home_model;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavouriteFragment extends Fragment {

    FragmentListener listener;
    private RecyclerView recycler_get_fav;
    GetFav_Adapter mAdapter;
    ProgressBar progressBar;
    TextView txt_empty_tutor;
    private SessionManager sessionManager;
    ArrayList<GetFavModelOne> modelList=new ArrayList<>();

    public FavouriteFragment(FragmentListener listener) {
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
        View view= inflater.inflate(R.layout.fragment_favourite, container, false);

        recycler_get_fav= view.findViewById(R.id.recycler_get_fav);
        progressBar= view.findViewById(R.id.progressBar);
        txt_empty_tutor= view.findViewById(R.id.txt_empty_tutor);
        sessionManager = new SessionManager(getActivity());

        modelList.clear();

        if (sessionManager.isNetworkAvailable()) {

            progressBar.setVisibility(View.VISIBLE);

            getFavApi();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }


        return  view;
    }

    private void setAdapter(ArrayList<GetFavModelOne> modelList) {

        mAdapter = new GetFav_Adapter(getActivity(), this.modelList,FavouriteFragment.this);

        recycler_get_fav.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recycler_get_fav.setLayoutManager(linearLayoutManager);
        recycler_get_fav.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new GetFav_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, GetFavModelOne model) {

            }
        });
    }

    public void getFavApi() {
        String UserId =  Preference.get(getActivity(),Preference.KEY_USER_ID);

        Call<GetFavModel> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_fav(UserId);

        call.enqueue(new Callback<GetFavModel>() {
            @Override
            public void onResponse(Call<GetFavModel> call, Response<GetFavModel> response) {

                try {

                    GetFavModel finallyPr = response.body();

                    progressBar.setVisibility(View.GONE);

                    String status   = finallyPr.getStatus ();
                    String message = finallyPr.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        modelList= (ArrayList<GetFavModelOne>) finallyPr.getResult();

                        setAdapter(modelList);

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
            public void onFailure(Call<GetFavModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                txt_empty_tutor.setVisibility(View.VISIBLE);
            }
        });

    }

}