package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.listner.onListClickListener;
import com.tech.twestudentside.model.TImeSlot;
import java.util.List;

public class DaySelectDayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: c */
    String f444c = "";
    private boolean isSelected = false;
    public onListClickListener listener;
    /* access modifiers changed from: private */
    public Context mContext;
    private OnItemClickListener mItemClickListener;
    private List<String> modelList;
    int pos = 0;
    int row_index = 0;
    String type = "";

    public interface OnItemClickListener {
        void onItemClick(View view, int i, String str);
    }

    public DaySelectDayAdapter(Context context, List<String> modelList2, String type2, onListClickListener listener2) {
        this.mContext = context;
        this.modelList = modelList2;
        this.type = type2;
        this.listener = listener2;
    }

    public void updateList(List<String> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.open_day, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            String item = getItem(position);
            final TImeSlot model1 = new TImeSlot();
            final ViewHolder genericViewHolder = (ViewHolder) holder;
            final String Name = this.modelList.get(position).toString();
            genericViewHolder.txt_day.setText(Name);
            if (!Name.equalsIgnoreCase("Tutor not available.")) {
                genericViewHolder.txt_day.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        TImeSlot tImeSlot = model1;
                        tImeSlot.setSelected(!tImeSlot.isSelected());
                        if (model1.isSelected()) {
                            DaySelectDayAdapter.this.listener.listClick(position, DaySelectDayAdapter.this.type, "add");
                            genericViewHolder.txt_day.setBackgroundResource(R.drawable.edit_background_red);
                            return;
                        }
                        DaySelectDayAdapter.this.listener.listClick(position, DaySelectDayAdapter.this.type, "remove");
                        genericViewHolder.txt_day.setBackgroundResource(R.drawable.color_gray);
                    }
                });
            } else {
                genericViewHolder.txt_day.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Toast.makeText(DaySelectDayAdapter.this.mContext, Name, Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private String getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout LL_time;
        /* access modifiers changed from: private */
        public TextView txt_day;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txt_day = (TextView) itemView.findViewById(R.id.txt_day);
            this.LL_time = (LinearLayout) itemView.findViewById(R.id.LL_time);
        }
    }
}
