package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.tech.twestudentside.R;
import com.tech.twestudentside.model.SearchDataModel;
import java.util.ArrayList;

public class Search_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<SearchDataModel> modelList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, SearchDataModel searchDataModel);
    }

    public Search_Adapter(Context context, ArrayList<SearchDataModel> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<SearchDataModel> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_search, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            SearchDataModel model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txt_tutorName.setText(model.getUsername());
            genericViewHolder.txt_gender.setText(model.getTutorDetails().getGender());
            genericViewHolder.txt_distance.setText(model.getTutorDetails().getTeachDistance());
            TextView access$300 = genericViewHolder.txt_suject;
            access$300.setText("Subjec : " + model.getTutorDetails().getSubjectName());
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private SearchDataModel getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_Dob;
        /* access modifiers changed from: private */
        public TextView txt_distance;
        /* access modifiers changed from: private */
        public TextView txt_gender;
        private TextView txt_price;
        /* access modifiers changed from: private */
        public TextView txt_suject;
        /* access modifiers changed from: private */
        public TextView txt_tutorName;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.txt_tutorName = (TextView) itemView.findViewById(R.id.txt_tutorName);
            this.txt_Dob = (TextView) itemView.findViewById(R.id.txt_Dob);
            this.txt_gender = (TextView) itemView.findViewById(R.id.txt_gender);
            this.txt_price = (TextView) itemView.findViewById(R.id.txt_price);
            this.txt_distance = (TextView) itemView.findViewById(R.id.txt_distance);
            this.txt_suject = (TextView) itemView.findViewById(R.id.txt_suject);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    Search_Adapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (SearchDataModel) Search_Adapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
