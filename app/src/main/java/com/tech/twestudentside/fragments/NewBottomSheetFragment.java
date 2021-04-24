package com.tech.twestudentside.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.MyLessonPackageActivity;
import com.tech.twestudentside.adapter.DaySelectTimeAdapter;
import com.tech.twestudentside.model.DaysModel;
import java.util.ArrayList;

public class NewBottomSheetFragment extends BottomSheetDialogFragment {

    /* renamed from: s2 */
    private static String f447s2;
    LinearLayout bokkingSit;
    private DaySelectTimeAdapter mAdapter;
    private ArrayList<DaysModel> modelList = new ArrayList<>();
    RecyclerView recycler_time_slot;
    TextView txt_time_select;

    public static NewBottomSheetFragment newInstance(String s1) {
        NewBottomSheetFragment fragment = new NewBottomSheetFragment();
        f447s2 = s1;
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void setupDialog(Dialog dialog, int style) {
        View contentView = View.inflate(getContext(), R.layout.fragment_new_bottom_sheet, (ViewGroup) null);
        dialog.setContentView(contentView);
        ((View) contentView.getParent()).setBackgroundColor(getResources().getColor(android.R.color.transparent));
        this.txt_time_select = (TextView) contentView.findViewById(R.id.txt_time_select);
        this.bokkingSit = (LinearLayout) contentView.findViewById(R.id.bokkingSit);
        this.recycler_time_slot = (RecyclerView) contentView.findViewById(R.id.recycler_time_slot);
        this.bokkingSit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NewBottomSheetFragment.this.startActivity(new Intent(NewBottomSheetFragment.this.getActivity(), MyLessonPackageActivity.class));
            }
        });
        if (f447s2.equalsIgnoreCase("1")) {
            this.txt_time_select.setText("Morning");
            addMorningSelect();
        } else if (f447s2.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
            this.txt_time_select.setText("Afternoon");
            addOfterNoonSelect();
        } else if (f447s2.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
            this.txt_time_select.setText("Evening");
            eveningTimeSelect();
        }
        setAdapterDay();
    }

    private void setAdapterDay() {
        this.mAdapter = new DaySelectTimeAdapter(getActivity(), this.modelList);
        this.recycler_time_slot.setHasFixedSize(true);
        this.recycler_time_slot.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));
        this.recycler_time_slot.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new DaySelectTimeAdapter.OnItemClickListener() {
            public void onItemClick(View view, int position, DaysModel model) {
            }
        });
    }

    private void addMorningSelect() {
        this.modelList.clear();
        this.modelList.add(new DaysModel("7-8"));
        this.modelList.add(new DaysModel("8-9"));
        this.modelList.add(new DaysModel("9-10"));
        this.modelList.add(new DaysModel("10-11"));
        this.modelList.add(new DaysModel("11-12"));
    }

    private void addOfterNoonSelect() {
        this.modelList.clear();
        this.modelList.add(new DaysModel("12-1"));
        this.modelList.add(new DaysModel("1-2"));
        this.modelList.add(new DaysModel("2-3"));
        this.modelList.add(new DaysModel("3-4"));
        this.modelList.add(new DaysModel("4-5"));
    }

    private void eveningTimeSelect() {
        this.modelList.clear();
        this.modelList.add(new DaysModel("6-7"));
        this.modelList.add(new DaysModel("7-8"));
        this.modelList.add(new DaysModel("8-9"));
        this.modelList.add(new DaysModel("9-10"));
        this.modelList.add(new DaysModel("10-11"));
    }
}
