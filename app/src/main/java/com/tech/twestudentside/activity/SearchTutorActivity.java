package com.tech.twestudentside.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.adapter.Search_Adapter;
import com.tech.twestudentside.model.SearchDataModel;
import com.tech.twestudentside.model.SearchModel;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.util.ArrayList;
import java.util.Iterator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTutorActivity extends AppCompatActivity {
    ArrayList<SearchDataModel> arrayList = new ArrayList<>();
    Search_Adapter mAdapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    EditText search_home;
    private SessionManager sessionManager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.search_home = (EditText) findViewById(R.id.search_home);
        this.sessionManager = new SessionManager((Activity) this);
        this.search_home.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() <= 0) {
                    SearchTutorActivity.this.recyclerView.setVisibility(View.GONE);
                } else {
                    SearchTutorActivity.this.recyclerView.setVisibility(View.VISIBLE);
                }
            }

            public void afterTextChanged(Editable editable) {
                SearchTutorActivity.this.filter(editable.toString());
            }
        });
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getSeacrh();
            return;
        }
        Toast.makeText(this, R.string.checkInternet, Toast.LENGTH_LONG).show();
    }

    /* access modifiers changed from: package-private */
    public void filter(String text) {
        ArrayList arrayList2 = new ArrayList();
        Iterator<SearchDataModel> it = this.arrayList.iterator();
        while (it.hasNext()) {
            SearchDataModel d = it.next();
            if (d.getTutorDetails().getSubjectName().toLowerCase().contains(text.toString().toLowerCase())) {
                arrayList2.add(d);
            }
            this.mAdapter.updateList(arrayList2);
        }
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<SearchDataModel> arrayList2) {
        this.mAdapter = new Search_Adapter(this, this.arrayList);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new Search_Adapter.OnItemClickListener() {
            public void onItemClick(View view, int position, SearchDataModel model) {
            }
        });
    }

    private void getSeacrh() {
        RetrofitClients.
                getInstance().
                getApi().
                get_search_tutor("").enqueue(new Callback<SearchModel>() {
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                SearchTutorActivity.this.progressBar.setVisibility(View.GONE);
                try {
                    SearchModel finallyPr = response.body();
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        SearchTutorActivity.this.arrayList = (ArrayList) finallyPr.getResult();
                        SearchTutorActivity.this.setAdapter(SearchTutorActivity.this.arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<SearchModel> call, Throwable t) {
                SearchTutorActivity.this.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
