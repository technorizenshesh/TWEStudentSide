package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.activity.TrandingActivity;
import com.tech.twestudentside.adapter.AdapterRecomended;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.home_model;
import com.tech.twestudentside.model.home_model_data;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Want_TutorFragment extends Fragment {
    String LocationId = "";
    private String android_id;
    ArrayList<home_model_data> arrayList;
    LinearLayout gridViewId;
    int[] icons = {R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse, R.drawable.penthouse};
    FragmentListener listener;
    LinearLayout mapViewId;
    LinearLayout myPropertyId;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    private SessionManager sessionManager;
    TextView txt_empty_tutor;

    public Want_TutorFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_want__tutor, container, false);
        this.LocationId = Preference.get(getActivity(), Preference.KEY_location_id);
        this.sessionManager = new SessionManager((Activity) getActivity());
        this.gridViewId = (LinearLayout) view.findViewById(R.id.grid_ViewId);
        this.txt_empty_tutor = (TextView) view.findViewById(R.id.txt_empty_tutor);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.gridViewId.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Want_TutorFragment.this.gridViewId.setBackgroundDrawable(Want_TutorFragment.this.getResources().getDrawable(R.drawable.color_pink));
                Want_TutorFragment.this.mapViewId.setBackgroundDrawable(Want_TutorFragment.this.getResources().getDrawable(R.drawable.color_gray));
                Want_TutorFragment.this.listener.click(new Want_TutorFragment(Want_TutorFragment.this.listener));
            }
        });
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.map_ViewId);
        this.mapViewId = linearLayout;
        linearLayout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Want_TutorFragment.this.gridViewId.setBackgroundDrawable(Want_TutorFragment.this.getResources().getDrawable(R.drawable.color_gray));
                Want_TutorFragment.this.mapViewId.setBackgroundDrawable(Want_TutorFragment.this.getResources().getDrawable(R.drawable.color_pink));
                //Want_TutorFragment.this.listener.click(new MapFragmentNew(Want_TutorFragment.this.listener));
            }
        });
        this.recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.arrayList = new ArrayList<>();
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getHome();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.myPropertyId);
        this.myPropertyId = linearLayout2;
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Want_TutorFragment.this.startActivity(new Intent(Want_TutorFragment.this.getActivity(), TrandingActivity.class));
            }
        });
        return view;
    }

    private void getHome() {
        String UserId = this.sessionManager.getUserID();
        RetrofitClients.getInstance().getApi().get_tutor(Preference.get(getActivity(), Preference.key_Type), UserId).enqueue(new Callback<home_model>() {
            public void onResponse(Call<home_model> call, Response<home_model> response) {
                try {
                    home_model finallyPr = response.body();
                    Want_TutorFragment.this.progressBar.setVisibility(View.GONE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        Want_TutorFragment.this.arrayList = (ArrayList) finallyPr.getResult();
                        Want_TutorFragment.this.recyclerView.setAdapter(new AdapterRecomended(Want_TutorFragment.this.getActivity(), Want_TutorFragment.this.arrayList));
                        Toast.makeText(Want_TutorFragment.this.getActivity(), message, Toast.LENGTH_LONG).show();
                        return;
                    }
                    Want_TutorFragment.this.progressBar.setVisibility(View.GONE);
                    Want_TutorFragment.this.txt_empty_tutor.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                    Want_TutorFragment.this.txt_empty_tutor.setVisibility(View.VISIBLE);
                }
            }

            public void onFailure(Call<home_model> call, Throwable t) {
                Want_TutorFragment.this.progressBar.setVisibility(View.GONE);
                Want_TutorFragment.this.txt_empty_tutor.setVisibility(View.VISIBLE);
            }
        });
    }
}
