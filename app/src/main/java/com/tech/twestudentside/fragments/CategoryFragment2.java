package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private String android_id;
    CheckBox category_layoutcard2;
    FragmentListener listener;
    tutor_sub_category_Adapter mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<TutorSubDataCategory> modelList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private RecyclerView recycler_get__sub_tutor_category;
    private SessionManager sessionManager;

    public CategoryFragment2(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category2, container, false);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.recycler_get__sub_tutor_category = (RecyclerView) view.findViewById(R.id.recycler_get__sub_tutor_category);
        this.sessionManager = new SessionManager((Activity) getActivity());
        this.modelList.clear();
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            tutorCategory();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet,Toast.LENGTH_LONG ).show();
        }
        return view;
    }

    private void tutorCategory() {
        RetrofitClients.getInstance().getApi().get_tutor_sub_category(Preference.get(getActivity(), Preference.KEY_tutor_category_id)).enqueue(new Callback<TutorSubCategory>() {
            public void onResponse(Call<TutorSubCategory> call, Response<TutorSubCategory> response) {
                try {
                    TutorSubCategory finallyPr = response.body();
                    CategoryFragment2.this.progressBar.setVisibility(View.GONE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        ArrayList unused = CategoryFragment2.this.modelList = (ArrayList) finallyPr.getResult();
                        CategoryFragment2.this.setAdapter(CategoryFragment2.this.modelList);
                        Toast.makeText(CategoryFragment2.this.getActivity(), message, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(CategoryFragment2.this.getActivity(), message, Toast.LENGTH_LONG).show();
                    CategoryFragment2.this.progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<TutorSubCategory> call, Throwable t) {
                CategoryFragment2.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(CategoryFragment2.this.getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<TutorSubDataCategory> modelList2) {
        this.mAdapter = new tutor_sub_category_Adapter(getActivity(), modelList2);
        this.recycler_get__sub_tutor_category.setHasFixedSize(true);
        this.recycler_get__sub_tutor_category.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler_get__sub_tutor_category.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new tutor_sub_category_Adapter.OnItemClickListener() {
            public void onItemClick(View view, int position, TutorSubDataCategory model) {
                Preference.save(CategoryFragment2.this.getActivity(), Preference.KEY_tutor_category_sub_id, model.getId());
                CategoryFragment2.this.listener.click(new CategoryFragment3(CategoryFragment2.this.listener));
            }
        });
    }
}
