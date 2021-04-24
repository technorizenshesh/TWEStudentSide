package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.R;
import com.tech.twestudentside.model.GetChatData;
import java.util.ArrayList;

public class Chat_conversasion_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<GetChatData> modelList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, GetChatData getChatData);
    }

    public Chat_conversasion_Adapter(Context context, ArrayList<GetChatData> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<GetChatData> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_conversation, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            GetChatData model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            if (Preference.get(this.mContext, Preference.KEY_ReceiverId).equalsIgnoreCase(model.getReceiverId())) {
                genericViewHolder.LL_right_layOut.setVisibility(View.VISIBLE);
                genericViewHolder.LL_left_layOut.setVisibility(View.GONE);
                genericViewHolder.txt_Right.setText(model.getChatMessage());
                genericViewHolder.txt_status_Right.setText(model.getStatus());
                return;
            }
            genericViewHolder.LL_right_layOut.setVisibility(View.GONE);
            genericViewHolder.LL_left_layOut.setVisibility(View.VISIBLE);
            genericViewHolder.txt_left.setText(model.getChatMessage());
            genericViewHolder.txt_status_left.setText(model.getStatus());
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private GetChatData getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public LinearLayout LL_left_layOut;
        /* access modifiers changed from: private */
        public LinearLayout LL_right_layOut;
        /* access modifiers changed from: private */
        public TextView txt_Right;
        /* access modifiers changed from: private */
        public TextView txt_left;
        /* access modifiers changed from: private */
        public TextView txt_status_Right;
        /* access modifiers changed from: private */
        public TextView txt_status_left;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.LL_left_layOut = (LinearLayout) itemView.findViewById(R.id.LL_left_layOut);
            this.LL_right_layOut = (LinearLayout) itemView.findViewById(R.id.LL_right_layOut);
            this.txt_left = (TextView) itemView.findViewById(R.id.txt_left);
            this.txt_Right = (TextView) itemView.findViewById(R.id.txt_Right);
            this.txt_status_left = (TextView) itemView.findViewById(R.id.txt_status_left);
            this.txt_status_Right = (TextView) itemView.findViewById(R.id.txt_status_Right);
            /*itemView.setOnClickListener(new View.OnClickListener(Chat_conversasion_Adapter.this) {
                public void onClick(View view) {
                    Chat_conversasion_Adapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (GetChatData) Chat_conversasion_Adapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });*/
        }
    }
}
