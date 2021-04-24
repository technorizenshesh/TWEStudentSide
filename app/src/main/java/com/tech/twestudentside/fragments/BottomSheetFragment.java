package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.share.internal.ShareConstants;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tech.twestudentside.GPSTracker;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.MapActivity;
import com.tech.twestudentside.adapter.GetAddress;
import com.tech.twestudentside.listner.MyClickListner;
import com.tech.twestudentside.model.getAddress;
import com.tech.twestudentside.model.getShiipingAddressData;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private static final int REQUEST_CODE = 1353;
    private LinearLayout LL_Add_address;
    private LinearLayout LL_CurrentLocation;
    LinearLayout LL_save;
    ImageView bottomSheet_cancelId;
    GPSTracker gpsTracker;
    int increment = 4;
    MyClickListner listner;
    GetAddress mAdapter;
    /* access modifiers changed from: private */
    public ArrayList<getShiipingAddressData> modelList = new ArrayList<>();
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    RecyclerView recycler_address;
    private SessionManager sessionManager;
    /* access modifiers changed from: private */
    public TextView txt_empty;
    private TextView txt_save;

    public BottomSheetFragment(MyClickListner listner2) {
        this.listner = listner2;
    }

    public BottomSheetFragment() {
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, (ViewGroup) null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        this.bottomSheet_cancelId = (ImageView) contentView.findViewById(R.id.bottomSheet_cancelId);
        this.LL_CurrentLocation = (LinearLayout) contentView.findViewById(R.id.LL_CurrentLocation);
        this.recycler_address = (RecyclerView) contentView.findViewById(R.id.recycler_address);
        this.sessionManager = new SessionManager((Activity) getActivity());
        this.progressBar = (ProgressBar) contentView.findViewById(R.id.progressBar);
        this.LL_CurrentLocation = (LinearLayout) contentView.findViewById(R.id.LL_CurrentLocation);
        this.txt_save = (TextView) contentView.findViewById(R.id.txt_save);
        this.LL_Add_address = (LinearLayout) contentView.findViewById(R.id.LL_Add_address);
        this.txt_empty = (TextView) contentView.findViewById(R.id.txt_empty);
        this.LL_save = (LinearLayout) contentView.findViewById(R.id.LL_save);
        this.bottomSheet_cancelId.setOnClickListener(this);
        this.LL_CurrentLocation.setOnClickListener(this);
        this.modelList.clear();
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getAddress();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        this.LL_save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BottomSheetFragment.this.listner.clickListen("harshit");
                BottomSheetFragment.this.dismiss();
            }
        });
        this.LL_Add_address.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.LL_CurrentLocation) {
            startActivityForResult(new Intent(getActivity(), MapActivity.class), REQUEST_CODE);
        } else if (id == R.id.bottomSheet_cancelId) {
            dismiss();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE) {
            return;
        }
        if (this.sessionManager.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getAddress();
            return;
        }
        Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<getShiipingAddressData> modelList2) {
        this.mAdapter = new GetAddress(getActivity(), modelList2);
        this.recycler_address.setHasFixedSize(true);
        this.recycler_address.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recycler_address.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new GetAddress.OnItemClickListener() {
            public void onItemClick(View view, int position, getShiipingAddressData model) {
            }
        });
    }

    private void AddAddress() {
        RetrofitClients.getInstance().getApi().add_address(this.sessionManager.getUserID(), "Home", "Sagar", "23.8388", "78.7378").enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    String status = jsonObject.getString("status");
                    String message = jsonObject.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                    JSONObject jSONObject = jsonObject.getJSONObject("result");
                    if (status.equalsIgnoreCase("1")) {
                        BottomSheetFragment.this.progressBar.setVisibility(View.GONE);
                        return;
                    }
                    Toast.makeText(BottomSheetFragment.this.getActivity(), message, Toast.LENGTH_LONG).show();
                    BottomSheetFragment.this.progressBar.setVisibility(View.GONE);
                    Toast.makeText(BottomSheetFragment.this.getActivity(), message, Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                BottomSheetFragment.this.progressBar.setVisibility(View.GONE);
                Toast.makeText(BottomSheetFragment.this.getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getAddress() {
        RetrofitClients.getInstance().getApi().get_address(Preference.get(getActivity(), Preference.KEY_USER_ID)).enqueue(new Callback<getAddress>() {
            public void onResponse(Call<getAddress> call, Response<getAddress> response) {
                BottomSheetFragment.this.progressBar.setVisibility(View.GONE);
                try {
                    getAddress myProductList = response.body();
                    String status = myProductList.getStatus().toString();
                    String Message = myProductList.getMessage().toString();
                    if (status.equalsIgnoreCase("1")) {
                        BottomSheetFragment.this.txt_empty.setVisibility(View.GONE);
                        ArrayList unused = BottomSheetFragment.this.modelList = (ArrayList) myProductList.getResult();
                        BottomSheetFragment.this.setAdapter(BottomSheetFragment.this.modelList);
                        return;
                    }
                    BottomSheetFragment.this.txt_empty.setVisibility(View.VISIBLE);
                    Toast.makeText(BottomSheetFragment.this.getActivity(), Message, Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    BottomSheetFragment.this.txt_empty.setVisibility(View.VISIBLE);
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<getAddress> call, Throwable t) {
                BottomSheetFragment.this.progressBar.setVisibility(View.GONE);
                BottomSheetFragment.this.txt_empty.setVisibility(View.VISIBLE);
                Toast.makeText(BottomSheetFragment.this.getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
