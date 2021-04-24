package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
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
    private String android_id;
    FragmentListener listener;
    tutor_category_Adapter mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<TutorCategoryModel> modelList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private RecyclerView recycler_get_tutor_category;
    private SessionManager sessionManager;

    public CategoryFragment1(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category1, container, false);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.recycler_get_tutor_category = (RecyclerView) view.findViewById(R.id.recycler_get_tutor_category);
        SessionManager sessionManager2 = new SessionManager((Activity) getActivity());
        this.sessionManager = sessionManager2;
        if (sessionManager2.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            tutorCategory();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void tutorCategory() {
        RetrofitClients.getInstance().getApi().get_tutor_category().enqueue(new Callback<TutorCategory_Model>() {
            public void onResponse(Call<TutorCategory_Model> call, Response<TutorCategory_Model> response) {
                try {
                    TutorCategory_Model finallyPr = response.body();
                    CategoryFragment1.this.progressBar.setVisibility(View.GONE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        ArrayList unused = CategoryFragment1.this.modelList = (ArrayList) finallyPr.getResult();
                        CategoryFragment1.this.setAdapter(CategoryFragment1.this.modelList);
                        Toast.makeText(CategoryFragment1.this.getActivity(), message, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(CategoryFragment1.this.getActivity(), message, Toast.LENGTH_LONG).show();
                    CategoryFragment1.this.progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<TutorCategory_Model> call, Throwable t) {
                CategoryFragment1.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(CategoryFragment1.this.getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<TutorCategoryModel> modelList2) {
        this.mAdapter = new tutor_category_Adapter(getActivity(), modelList2);
        this.recycler_get_tutor_category.setHasFixedSize(true);
        new LinearLayoutManager(getActivity());
        this.recycler_get_tutor_category.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        this.recycler_get_tutor_category.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new tutor_category_Adapter.OnItemClickListener() {
            public void onItemClick(View view, int position, TutorCategoryModel model) {
                Preference.save(CategoryFragment1.this.getActivity(), Preference.KEY_tutor_category_id, model.getId());
                CategoryFragment1.this.listener.click(new CategoryFragment2(CategoryFragment1.this.listener));
            }
        });
    }
}
