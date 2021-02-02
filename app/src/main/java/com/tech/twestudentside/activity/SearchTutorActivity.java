package com.tech.twestudentside.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.AdapterRecomended;
import com.tech.twestudentside.adapter.GetFav_Adapter;
import com.tech.twestudentside.adapter.Search_Adapter;
import com.tech.twestudentside.fragments.FavouriteFragment;
import com.tech.twestudentside.model.GetFavModelOne;
import com.tech.twestudentside.model.home_model;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTutorActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    ProgressBar progressBar;
    ArrayList<home_model_data> arrayList = new ArrayList<>();
    Search_Adapter mAdapter;
    private SessionManager sessionManager;
    EditText search_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor);


        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progressBar);
        search_home = findViewById(R.id.search_home);
        sessionManager = new SessionManager(this);

        if (sessionManager.isNetworkAvailable()) {

            progressBar.setVisibility(View.VISIBLE);

            getSeacrh();

        }else {

            Toast.makeText(SearchTutorActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();

        }


    }

    private void setAdapter(ArrayList<home_model_data> arrayList) {

        mAdapter = new Search_Adapter(SearchTutorActivity.this, this.arrayList);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchTutorActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new Search_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, home_model_data model) {

            }
        });
    }

    private void getSeacrh() {

        String UserId =  sessionManager.getUserID();

        String type = "";

        Call<home_model> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_tutor(type,UserId);
        call.enqueue(new Callback<home_model>() {
            @Override
            public void onResponse(Call<home_model> call, Response<home_model> response) {

                progressBar.setVisibility(View.GONE);

                try {

                    home_model finallyPr = response.body();

                    //JSONObject jsonObject = new JSONObject(response.body().string());

                    String status   = finallyPr.getStatus ();
                    String message = finallyPr.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        arrayList = (ArrayList<home_model_data>) finallyPr.getResult();

                        setAdapter(arrayList);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<home_model> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
            }
        });

    }


}