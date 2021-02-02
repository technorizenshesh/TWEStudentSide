package com.tech.twestudentside.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.UploadProfilePicActivity;
import com.tech.twestudentside.adapter.tutor_subject_Adapter;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.TutorSubjectDataModel;
import com.tech.twestudentside.model.TutorSubjectMode;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment3 extends Fragment {

    FragmentListener listener;

    private SessionManager sessionManager;
    private String android_id;
    private ProgressBar progressBar;

    private LinearLayout LL_choose_sub;
    private RecyclerView recycler_choose_subject;
    tutor_subject_Adapter mAdapter;
    private ArrayList<TutorSubjectDataModel> modelList = new ArrayList<>();

    public CategoryFragment3(FragmentListener listener) {
        this.listener=listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_category3, container, false);

       /* category_layoutcard3=view.findViewById(R.id.category_layoutcard3);
        category_layoutcard3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), UploadProfilePicActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });*/

       // category_layoutcard3=view.findViewById(R.id.category_layoutcard3);
        progressBar=view.findViewById(R.id.progressBar);
        recycler_choose_subject=view.findViewById(R.id.recycler_choose_subject);
         LL_choose_sub=view.findViewById(R.id.LL_choose_sub);
        sessionManager = new SessionManager(getActivity());

        modelList.clear();

        LL_choose_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), UploadProfilePicActivity.class));
                Objects.requireNonNull(getActivity()).finish();

            }
        });

        /*category_layoutcard3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), FeeCalculatorActivity.class));
                Objects.requireNonNull(getActivity()).finish();
            }
        });*/

        if (sessionManager.isNetworkAvailable()) {

            //LL_submit.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);

            tutorSubject();

        }else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_SHORT).show();
        }


        return view;
    }


    private void tutorSubject() {

        String Category_id =  Preference.get(getActivity(),Preference.KEY_tutor_category_id);
        String Sub_Category_id =  Preference.get(getActivity(),Preference.KEY_tutor_category_sub_id);

        Call<TutorSubjectMode> call = RetrofitClients
                .getInstance()
                .getApi()
                .get_tutor_subject(Category_id,Sub_Category_id);

        call.enqueue(new Callback<TutorSubjectMode>() {
            @Override
            public void onResponse(Call<TutorSubjectMode> call, Response<TutorSubjectMode> response) {

                try {

                    TutorSubjectMode finallyPr = response.body();

                    progressBar.setVisibility(View.GONE);

                    //JSONObject jsonObject = new JSONObject(response.body().string());

                    String status   = finallyPr.getStatus ();
                    String message = finallyPr.getMessage();

                    if (status.equalsIgnoreCase("1")) {

                        modelList = (ArrayList<TutorSubjectDataModel>) finallyPr.getResult();

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
            public void onFailure(Call<TutorSubjectMode> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                //    LL_submit.setEnabled(true);
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setAdapter(ArrayList<TutorSubjectDataModel> modelList) {

        mAdapter = new tutor_subject_Adapter(getActivity(), modelList);

        recycler_choose_subject.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        recycler_choose_subject.setLayoutManager(linearLayoutManager);

        recycler_choose_subject.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener(new tutor_subject_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, TutorSubjectDataModel model) {

            }
        });
    }
}