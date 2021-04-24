package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.fragments.TodayClassDetailFragment;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.model.ClassTodayModal;
import java.util.ArrayList;
import java.util.List;

public class ClassTodayAdapter extends RecyclerView.Adapter<ClassTodayAdapter.ViewHolder> {
    private List<ClassTodayModal> classTodayModalList = new ArrayList();
    private Context context;
    FragmentListener listener;

    public interface OnRecyclerViewItemClickListener {
        void onClick(int i);
    }

    public ClassTodayAdapter(FragmentListener listener2) {
        this.listener = listener2;
    }

    public ClassTodayAdapter(Context context2, List<ClassTodayModal> classTodayModal) {
        this.context = context2;
        this.classTodayModalList = classTodayModal;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.items_class_today, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.cardTodayInit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                ((ViewGroup) activity.findViewById(R.id.frameContainerRequest)).removeAllViews();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameContainerRequest, new TodayClassDetailFragment(ClassTodayAdapter.this.listener)).addToBackStack((String) null).commit();
            }
        });
    }

    public int getItemCount() {
        return 3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardTodayInit;

        public ViewHolder(View itemView) {
            super(itemView);
            this.cardTodayInit = (CardView) itemView.findViewById(R.id.cardTodayInit);
        }
    }
}
