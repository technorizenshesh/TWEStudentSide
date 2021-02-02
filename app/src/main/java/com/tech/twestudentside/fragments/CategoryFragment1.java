package com.tech.twestudentside.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.CategorySelectActivity;
import com.tech.twestudentside.adapter.tutor_category_Adapter;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.TutorCategoryModel;
import com.tech.twestudentside.model.TutorCategory_Model;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment1 extends Fragment {

    FragmentListener listener;

    private SessionManager sessionManager;
    private String android_id;
    private ProgressBar progressBar;

    private RecyclerView recycler_get_tutor_category;
    tutor_category_Adapter mAdapter;
    private ArrayList<TutorCategoryModel> modelList = new ArrayList<>();

    public CategoryFragment1(FragmentListener listener) {
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
        View view= inflater.inflate(R.layout.fragment_category1, container, false);


        progressBar=view.findViewById(R.id.progressBar);
        recycler_get_tutor_category=view.findViewById(R.id.recycler_get_tutor_category);
        sessionManager = new SessionManager(getActivity());

        if (sessionManager.isNetworkAvailable()) {

            //LL_submit.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);

            tutorCategory();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    private void tutorCategory() {

        Call<TutorCategory_Model> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_tutor_category();

        call.enqueue(new Callback<TutorCategory_Model>() {
            @Override
            public void onResponse(Call<TutorCategory_Model> call, Response<TutorCategory_Model> response) {

                try {

                    TutorCategory_Model finallyPr = response.body();

                    progressBar.setVisibility(View.GONE);

                    //JSONObject jsonObject = new JSONObject(response.body().string());

                    String status   = finallyPr.getStatus ();
                    String message = finallyPr.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        modelList = (ArrayList<TutorCategoryModel>) finallyPr.getResult();

                        setAdapter(modelList);

                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        //LL_submit.setEnabled(true);
                    }

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }

            @Override
            public void onFailure(Call<TutorCategory_Model> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                //    LL_submit.setEnabled(true);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter(ArrayList<TutorCategoryModel> modelList) {

        mAdapter = new tutor_category_Adapter(getActivity(), modelList);

        recycler_get_tutor_category.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recycler_get_tutor_category.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        recycler_get_tutor_category.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new tutor_category_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, TutorCategoryModel model) {

                Preference.save(getActivity(), Preference.KEY_tutor_category_id,model.getId());

                listener.click(new CategoryFragment2(listener));
            }
        });
    }
}