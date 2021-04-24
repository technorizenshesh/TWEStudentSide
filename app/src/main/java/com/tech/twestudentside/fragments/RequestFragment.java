package com.tech.twestudentside.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.ColorAnimation;
import com.tech.twestudentside.R;
import com.tech.twestudentside.listner.FragmentListener;
import java.util.Objects;

public class RequestFragment extends Fragment implements View.OnClickListener, FragmentListener {
    TextView completedId;
    ImageView iv_backRequest;
    FragmentListener listener;
    TextView scheduledId;

    public RequestFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request, container, false);
        TextView textView = (TextView) view.findViewById(R.id.scheduledId);
        this.scheduledId = textView;
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) view.findViewById(R.id.completedId);
        this.completedId = textView2;
        textView2.setOnClickListener(this);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_backRequest);
        this.iv_backRequest = imageView;
        imageView.setOnClickListener(this);
        loadFragment(new ScheduledFragment(this));
        return view;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.completedId) {
            this.scheduledId.setTextColor(Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR));
            this.completedId.setTextColor(Color.parseColor("#ff5d5d"));
            this.completedId.setTextSize(18.0f);
            this.scheduledId.setTextSize(16.0f);
            loadFragment(new ScheduledFragment(this));
        } else if (id == R.id.iv_backRequest) {
            ((FragmentActivity) Objects.requireNonNull(getActivity())).onBackPressed();
        } else if (id == R.id.scheduledId) {
            this.scheduledId.setTextColor(Color.parseColor("#ff5d5d"));
            this.completedId.setTextColor(Color.parseColor(ColorAnimation.DEFAULT_SELECTED_COLOR));
            this.scheduledId.setTextSize(18.0f);
            this.completedId.setTextSize(16.0f);
            loadFragment(new ScheduledFragment(this));
        }
    }

    private void loadFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction().replace(R.id.frameContainerRequest, fragment).addToBackStack("home").commit();
    }

    public void click(Fragment fragment) {
        loadFragment(fragment);
    }
}
