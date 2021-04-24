package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.model.TutorSubjectDataModel;
import java.util.ArrayList;

public class tutor_subject_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<TutorSubjectDataModel> modelList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, TutorSubjectDataModel tutorSubjectDataModel);
    }

    public tutor_subject_Adapter(Context context, ArrayList<TutorSubjectDataModel> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<TutorSubjectDataModel> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tutor_subject, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).check_subject.setText(getItem(position).getName());
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private TutorSubjectDataModel getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public CheckBox check_subject;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.check_subject = (CheckBox) itemView.findViewById(R.id.check_subject);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    tutor_subject_Adapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (TutorSubjectDataModel) tutor_subject_Adapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
