package com.tech.twestudentside.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.R;

public class CountrySpinnerAdapter extends BaseAdapter {
    String[] code;
    Context context;
    String[] countryNames;
    TextView countrycode;
    int[] flags;
    ImageView icon;
    LayoutInflater inflter;

    public CountrySpinnerAdapter(Context applicationContext, int[] flags2, String[] code2) {
        this.context = applicationContext;
        this.flags = flags2;
        this.code = code2;
        this.inflter = LayoutInflater.from(applicationContext);
    }

    public int getCount() {
        return this.code.length;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = this.inflter.inflate(R.layout.spinner_layout, (ViewGroup) null);
        TextView textView = (TextView) view2.findViewById(R.id.textview);
        this.countrycode = textView;
        textView.setText(this.code[i]);
        return view2;
    }
}
