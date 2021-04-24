package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.model.ChatConversationData;
import java.util.ArrayList;

public class ChatList_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    /* access modifiers changed from: private */
    public OnItemClickListener mItemClickListener;
    /* access modifiers changed from: private */
    public ArrayList<ChatConversationData> modelList;

    public interface OnItemClickListener {
        void onItemClick(View view, int i, ChatConversationData chatConversationData);
    }

    public ChatList_Adapter(Context context, ArrayList<ChatConversationData> modelList2) {
        this.mContext = context;
        this.modelList = modelList2;
    }

    public void updateList(ArrayList<ChatConversationData> modelList2) {
        this.modelList = modelList2;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_list, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ChatConversationData model = getItem(position);
            ViewHolder genericViewHolder = (ViewHolder) holder;
            genericViewHolder.txt_receiverName.setText(model.getUsername());
            genericViewHolder.txt_last_message.setText(model.getLastMessage());
            String ProfileImage = model.getImage().toString();
            if (ProfileImage != null) {
                ((RequestBuilder) Glide.with(this.mContext).load(ProfileImage).placeholder(R.drawable.home_banner3)).into((ImageView) genericViewHolder.img_profile);
            }
        }
    }

    public int getItemCount() {
        return this.modelList.size();
    }

    public void SetOnItemClickListener(OnItemClickListener mItemClickListener2) {
        this.mItemClickListener = mItemClickListener2;
    }

    private ChatConversationData getItem(int position) {
        return this.modelList.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /* access modifiers changed from: private */
        public RoundedImageView img_profile;
        /* access modifiers changed from: private */
        public TextView txt_last_message;
        /* access modifiers changed from: private */
        public TextView txt_receiverName;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.txt_receiverName = (TextView) itemView.findViewById(R.id.txt_receiverName);
            this.txt_last_message = (TextView) itemView.findViewById(R.id.txt_last_message);
            this.img_profile = (RoundedImageView) itemView.findViewById(R.id.img_profile);
            itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ChatList_Adapter.this.mItemClickListener.onItemClick(itemView, ViewHolder.this.getAdapterPosition(), (ChatConversationData) ChatList_Adapter.this.modelList.get(ViewHolder.this.getAdapterPosition()));
                }
            });
        }
    }
}
