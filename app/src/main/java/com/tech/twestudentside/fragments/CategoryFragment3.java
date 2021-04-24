package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
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
    private LinearLayout LL_choose_sub;
    private String android_id;
    FragmentListener listener;
    tutor_subject_Adapter mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<TutorSubjectDataModel> modelList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    private RecyclerView recycler_choose_subject;
    private SessionManager sessionManager;

    public CategoryFragment3(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category3, container, false);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.recycler_choose_subject = (RecyclerView) view.findViewById(R.id.recycler_choose_subject);
        this.LL_choose_sub = (LinearLayout) view.findViewById(R.id.LL_choose_sub);
        this.sessionManager = new SessionManager((Activity) getActivity());
        this.modelList.clear();
        this.LL_choose_sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                CategoryFragment3.this.startActivity(new Intent(CategoryFragment3.this.getActivity(), UploadProfilePicActivity.class));
                ((FragmentActivity) Objects.requireNonNull(CategoryFragment3.this.getActivity())).finish();
            }
        });
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            tutorSubject();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        return view;
    }

    private void tutorSubject() {
        RetrofitClients.getInstance().getApi().get_tutor_subject(Preference.get(getActivity(), Preference.KEY_tutor_category_id), Preference.get(getActivity(), Preference.KEY_tutor_category_sub_id)).enqueue(new Callback<TutorSubjectMode>() {
            public void onResponse(Call<TutorSubjectMode> call, Response<TutorSubjectMode> response) {
                try {
                    TutorSubjectMode finallyPr = response.body();
                    CategoryFragment3.this.progressBar.setVisibility(View.GONE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        ArrayList unused = CategoryFragment3.this.modelList = (ArrayList) finallyPr.getResult();
                        CategoryFragment3.this.setAdapter(CategoryFragment3.this.modelList);
                        Toast.makeText(CategoryFragment3.this.getActivity(), message, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Toast.makeText(CategoryFragment3.this.getActivity(), message, Toast.LENGTH_LONG).show();
                    CategoryFragment3.this.progressBar.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<TutorSubjectMode> call, Throwable t) {
                CategoryFragment3.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(CategoryFragment3.this.getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<TutorSubjectDataModel> modelList2) {
        this.mAdapter = new tutor_subject_Adapter(getActivity(), modelList2);
        this.recycler_choose_subject.setHasFixedSize(true);
        this.recycler_choose_subject.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler_choose_subject.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new tutor_subject_Adapter.OnItemClickListener() {
            public void onItemClick(View view, int position, TutorSubjectDataModel model) {
            }
        });
    }
}
