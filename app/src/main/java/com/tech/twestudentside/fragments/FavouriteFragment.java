package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.adapter.GetFav_Adapter;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.GetFavModel;
import com.tech.twestudentside.model.GetFavModelOne;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteFragment extends Fragment {
    FragmentListener listener;
    GetFav_Adapter mAdapter;
    ArrayList<GetFavModelOne> modelList = new ArrayList<>();
    ProgressBar progressBar;
    private RecyclerView recycler_get_fav;
    private SessionManager sessionManager;
    TextView txt_empty_tutor;

    public FavouriteFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        this.recycler_get_fav = (RecyclerView) view.findViewById(R.id.recycler_get_fav);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        this.txt_empty_tutor = (TextView) view.findViewById(R.id.txt_empty_tutor);
        this.sessionManager = new SessionManager((Activity) getActivity());
        this.modelList.clear();
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getFavApi();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<GetFavModelOne> arrayList) {
        this.mAdapter = new GetFav_Adapter(getActivity(), this.modelList, this);
        this.recycler_get_fav.setHasFixedSize(true);
        this.recycler_get_fav.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler_get_fav.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new GetFav_Adapter.OnItemClickListener() {
            public void onItemClick(View view, int position, GetFavModelOne model) {
            }
        });
    }

    public void getFavApi() {
        RetrofitClients.getInstance().getApi().get_fav(Preference.get(getActivity(), Preference.KEY_USER_ID)).enqueue(new Callback<GetFavModel>() {
            public void onResponse(Call<GetFavModel> call, Response<GetFavModel> response) {
                try {
                    GetFavModel finallyPr = response.body();
                    FavouriteFragment.this.progressBar.setVisibility(View.GONE);
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        FavouriteFragment.this.modelList = (ArrayList) finallyPr.getResult();
                        FavouriteFragment.this.setAdapter(FavouriteFragment.this.modelList);
                        return;
                    }
                    FavouriteFragment.this.progressBar.setVisibility(View.GONE);
                    FavouriteFragment.this.txt_empty_tutor.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                    FavouriteFragment.this.txt_empty_tutor.setVisibility(View.VISIBLE);
                }
            }

            public void onFailure(Call<GetFavModel> call, Throwable t) {
                FavouriteFragment.this.progressBar.setVisibility(View.GONE);
                FavouriteFragment.this.txt_empty_tutor.setVisibility(View.VISIBLE);
            }
        });
    }
}
