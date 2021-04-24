package com.tech.twestudentside.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.activity.ChatActivity;
import com.tech.twestudentside.adapter.ChatList_Adapter;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.ChatConversation;
import com.tech.twestudentside.model.ChatConversationData;
import com.tech.twestudentside.utils.RetrofitClients;
import com.tech.twestudentside.utils.SessionManager;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {
    ArrayList<ChatConversationData> arrayList = new ArrayList<>();
    ImageView iv_back;
    FragmentListener listener;
    ChatList_Adapter mAdapter;
    CardView message_card1;
    ProgressBar progressBar;
    private RecyclerView recycler_view_chat;
    private SessionManager sessionManager;

    public MessageFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        this.recycler_view_chat = (RecyclerView) view.findViewById(R.id.recycler_view_chat);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        SessionManager sessionManager2 = new SessionManager((Activity) getActivity());
        this.sessionManager = sessionManager2;
        if (sessionManager2.isNetworkAvailable()) {
            this.progressBar.setVisibility(View.VISIBLE);
            getConversationDetails();
        } else {
            Toast.makeText(getActivity(), R.string.checkInternet, Toast.LENGTH_LONG).show();
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void setAdapter(ArrayList<ChatConversationData> arrayList2) {
        this.mAdapter = new ChatList_Adapter(getActivity(), this.arrayList);
        this.recycler_view_chat.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        this.recycler_view_chat.setLayoutManager(linearLayoutManager);
        this.recycler_view_chat.setAdapter(this.mAdapter);
        this.mAdapter.SetOnItemClickListener(new ChatList_Adapter.OnItemClickListener() {
            public void onItemClick(View view, int position, ChatConversationData model) {
                Preference.save(MessageFragment.this.getActivity(), Preference.KEY_student_Name, model.getUsername());
                Preference.save(MessageFragment.this.getActivity(), Preference.KEY_ReceiverId, model.getReceiverId());
                Preference.save(MessageFragment.this.getActivity(), Preference.KEY_SenderId, model.getSenderId());
                MessageFragment.this.startActivity(new Intent(MessageFragment.this.getActivity(), ChatActivity.class));
            }
        });
    }

    private void getConversationDetails() {
        RetrofitClients.getInstance().getApi().get_conversation_detail("6").enqueue(new Callback<ChatConversation>() {
            public void onResponse(Call<ChatConversation> call, Response<ChatConversation> response) {
                MessageFragment.this.progressBar.setVisibility(View.GONE);
                try {
                    ChatConversation finallyPr = response.body();
                    String status = finallyPr.getStatus();
                    String message = finallyPr.getMessage();
                    if (status.equalsIgnoreCase("1")) {
                        MessageFragment.this.arrayList = (ArrayList) finallyPr.getResult();
                        MessageFragment.this.setAdapter(MessageFragment.this.arrayList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<ChatConversation> call, Throwable t) {
                MessageFragment.this.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
