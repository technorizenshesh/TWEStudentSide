package com.tech.twestudentside.fragments;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.tutor_sub_category_Adapter;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.TutorSubCategory;
import com.tech.twestudentside.model.TutorSubDataCategory;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CategoryFragment2 extends Fragment {

    FragmentListener listener;
    CheckBox category_layoutcard2;

    private SessionManager sessionManager;
    private String android_id;
    private ProgressBar progressBar;

    private RecyclerView recycler_get__sub_tutor_category;
    tutor_sub_category_Adapter mAdapter;
    private ArrayList<TutorSubDataCategory> modelList = new ArrayList<>();

    public CategoryFragment2(FragmentListener listener) {
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
        View view= inflater.inflate(R.layout.fragment_category2, container, false);


        progressBar=view.findViewById(R.id.progressBar);
        recycler_get__sub_tutor_category=view.findViewById(R.id.recycler_get__sub_tutor_category);
        sessionManager = new SessionManager(getActivity());

        modelList.clear();

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

        String Category_id =  Preference.get(getActivity(),Preference.KEY_tutor_category_id);

        Call<TutorSubCategory> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_tutor_sub_category(Category_id);

        call.enqueue(new Callback<TutorSubCategory>() {
            @Override
            public void onResponse(Call<TutorSubCategory> call, Response<TutorSubCategory> response) {

                try {

                    TutorSubCategory finallyPr = response.body();

                    progressBar.setVisibility(View.GONE);

                    //JSONObject jsonObject = new JSONObject(response.body().string());

                    String status   = finallyPr.getStatus ();
                    String message = finallyPr.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        modelList = (ArrayList<TutorSubDataCategory>) finallyPr.getResult();

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
            public void onFailure(Call<TutorSubCategory> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                //    LL_submit.setEnabled(true);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter(ArrayList<TutorSubDataCategory> modelList) {

        mAdapter = new tutor_sub_category_Adapter(getActivity(), modelList);

        recycler_get__sub_tutor_category.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recycler_get__sub_tutor_category.setLayoutManager(linearLayoutManager);

        recycler_get__sub_tutor_category.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new tutor_sub_category_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, TutorSubDataCategory model) {

                Preference.save(getActivity(),Preference.KEY_tutor_category_sub_id,model.getId());

                listener.click(new CategoryFragment3(listener));

            }
        });
    }
}