package com.tech.twestudentside.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.tech.twestudentside.R;
import com.tech.twestudentside.Preference;
import com.tech.twestudentside.adapter.DaySelectDayAdapter;
import com.tech.twestudentside.listner.onListClickListener;
import com.tech.twestudentside.model.GteTutorTime;
import com.tech.twestudentside.utils.RetrofitClients;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReserveSpotActivity extends AppCompatActivity implements onListClickListener {
    public static JSONArray friday = new JSONArray();
    public static JSONArray monday = new JSONArray();
    public static JSONArray saturday = new JSONArray();
    public static JSONArray sunday = new JSONArray();
    public static JSONArray thursday = new JSONArray();
    public static JSONArray tuesday = new JSONArray();
    public static JSONArray wednesday = new JSONArray();
    List<String> Fridaylist = new ArrayList();
    List<String> Monday_new = new ArrayList();
    List<String> Mondaylist = new ArrayList();
    private LinearLayout RR_date;
    List<String> Saturdaylist = new ArrayList();
    List<String> Sunday1list = new ArrayList();
    List<String> ThrusDaylist = new ArrayList();
    List<String> Tuesdaylist = new ArrayList();
    private DaySelectDayAdapter mAdapter;
    /* access modifiers changed from: private */
    public int mDay;
    /* access modifiers changed from: private */
    public int mMonth;
    /* access modifiers changed from: private */
    public int mYear;
    ProgressBar progressBar;
    /* access modifiers changed from: private */
    public RecyclerView recycler_available_slot_friday;
    /* access modifiers changed from: private */
    public RecyclerView recycler_available_slot_moday;
    /* access modifiers changed from: private */
    public RecyclerView recycler_available_slot_saturday;
    /* access modifiers changed from: private */
    public RecyclerView recycler_available_slot_sunday;
    /* access modifiers changed from: private */
    public RecyclerView recycler_available_slot_thursday;
    /* access modifiers changed from: private */
    public RecyclerView recycler_available_slot_tuesday;
    /* access modifiers changed from: private */
    public RecyclerView recycler_available_slot_wednesDay;
    String time_slot = ExifInterface.GPS_MEASUREMENT_2D;
    /* access modifiers changed from: private */
    public TextView txt1Tab;
    /* access modifiers changed from: private */
    public TextView txt3Tab;
    private TextView txt_afternoon_shift;
    /* access modifiers changed from: private */
    public TextView txt_dob;
    TextView txt_empty_tutor;
    private TextView txt_evening_shift;
    private TextView txt_morning_shift;
    private TextView txt_time_zone;
    List<String> wednesDaylist = new ArrayList();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) R.layout.activity_reserve_spot);
        TimeZone tz = TimeZone.getDefault();
        String TimeZone1 = tz.getDisplayName(false, 0);
        this.txt_time_zone = (TextView) findViewById(R.id.txt_time_zone);
        this.RR_date = (LinearLayout) findViewById(R.id.RR_date);
        this.txt_dob = (TextView) findViewById(R.id.txt_dob);
        this.txt1Tab = (TextView) findViewById(R.id.txt1Tab);
        this.txt3Tab = (TextView) findViewById(R.id.txt3Tab);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.txt_empty_tutor = (TextView) findViewById(R.id.txt_empty_tutor);
        this.recycler_available_slot_moday = (RecyclerView) findViewById(R.id.recycler_available_slot_moday);
        this.recycler_available_slot_tuesday = (RecyclerView) findViewById(R.id.recycler_available_slot_tuesday);
        this.recycler_available_slot_wednesDay = (RecyclerView) findViewById(R.id.recycler_available_slot_wednesDay);
        this.recycler_available_slot_thursday = (RecyclerView) findViewById(R.id.recycler_available_slot_thursday);
        this.recycler_available_slot_friday = (RecyclerView) findViewById(R.id.recycler_available_slot_friday);
        this.recycler_available_slot_saturday = (RecyclerView) findViewById(R.id.recycler_available_slot_saturday);
        this.recycler_available_slot_saturday = (RecyclerView) findViewById(R.id.recycler_available_slot_saturday);
        this.recycler_available_slot_sunday = (RecyclerView) findViewById(R.id.recycler_available_slot_sunday);
        String TimeZone = "( " + TimeZone1 + " ) " + tz.getDisplayName();
        Preference.save(this, Preference.KEY_time_zone, TimeZone);
        this.txt_time_zone.setText(TimeZone);
        this.RR_date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int unused = ReserveSpotActivity.this.mYear = c.get(1);
                int unused2 = ReserveSpotActivity.this.mMonth = c.get(2);
                int unused3 = ReserveSpotActivity.this.mDay = c.get(5);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ReserveSpotActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        view.setVisibility(View.VISIBLE);
                        String str = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                        String newDate = ReserveSpotActivity.this.getDate(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        Preference.save(ReserveSpotActivity.this, Preference.KEY_start_date, newDate);
                        ReserveSpotActivity.this.txt_dob.setText(newDate);
                    }
                }, ReserveSpotActivity.this.mYear, ReserveSpotActivity.this.mMonth, ReserveSpotActivity.this.mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });
        this.txt1Tab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Preference.save(ReserveSpotActivity.this, Preference.KEY_reserve_type, "Individual");
                ReserveSpotActivity.this.txt1Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorWhite));
                ReserveSpotActivity.this.txt3Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
                ReserveSpotActivity.this.txt1Tab.setBackgroundResource(R.drawable.color_pink);
                ReserveSpotActivity.this.txt3Tab.setBackgroundResource(R.drawable.color_gray);
            }
        });
        this.txt3Tab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Preference.save(ReserveSpotActivity.this, Preference.KEY_reserve_type, "Group");
                ReserveSpotActivity.this.txt1Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorBlack));
                ReserveSpotActivity.this.txt3Tab.setTextColor(ContextCompat.getColor(ReserveSpotActivity.this, R.color.colorWhite));
                ReserveSpotActivity.this.txt1Tab.setBackgroundResource(R.drawable.color_gray);
                ReserveSpotActivity.this.txt3Tab.setBackgroundResource(R.drawable.color_pink);
            }
        });
        getTutotTime();
    }

    public void backPressReserveSpot(View view) {
        onBackPressed();
        finish();
    }

    public void ReserveBtnInit(View view) {
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONArray jSONArray3;
        JSONArray jSONArray4;
        JSONArray jSONArray5;
        JSONArray jSONArray6;
        JSONArray jSONArray7 = monday;
        if ((jSONArray7 == null || jSONArray7.length() <= 0) && (((jSONArray = tuesday) == null || jSONArray.length() <= 0) && (((jSONArray2 = wednesday) == null || jSONArray2.length() <= 0) && (((jSONArray3 = thursday) == null || jSONArray3.length() <= 0) && (((jSONArray4 = friday) == null || jSONArray4.length() <= 0) && (((jSONArray5 = saturday) == null || jSONArray5.length() <= 0) && ((jSONArray6 = sunday) == null || jSONArray6.length() <= 0))))))) {
            Toast.makeText(this, "Please Select time for Classes.", Toast.LENGTH_LONG).show();
        } else {
            startActivity(new Intent(this, MyLessonPackageActivity.class));
        }
    }

    /* access modifiers changed from: private */
    public void setAdapterDay(List<String> modelList, RecyclerView recyclerView, String Type) {
        this.mAdapter = new DaySelectDayAdapter(this, modelList, Type, this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.mAdapter);
    }

    /* access modifiers changed from: private */
    public String getDate(String Date) {
        Date dateObj = null;
        try {
            dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new SimpleDateFormat("dd MMMM  yyyy").format(dateObj);
    }

    private void getTutotTime() {
        RetrofitClients.getInstance().getApi().get_tutor_time(Preference.get(this, Preference.KEY_Tutor_id)).enqueue(new Callback<GteTutorTime>() {
            public void onResponse(Call<GteTutorTime> call, Response<GteTutorTime> response) {
                GteTutorTime finallyPr = response.body();
                ReserveSpotActivity.this.progressBar.setVisibility(View.GONE);
                String status = finallyPr.getStatus();
                String message = finallyPr.getMessage();
                if (status.equalsIgnoreCase("1")) {
                    String mondayDay = finallyPr.getResult().getMonday();
                    JSONArray jsonMondayDay = null;
                    try {
                        jsonMondayDay = new JSONArray(mondayDay);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!mondayDay.equalsIgnoreCase("[]")) {
                        int i = 0;
                        while (i < jsonMondayDay.length()) {
                            try {
                                ReserveSpotActivity.this.Mondaylist.add(jsonMondayDay.get(i).toString());
                                i++;
                            } catch (Exception e) {
                                e.printStackTrace();
                                ReserveSpotActivity.this.txt_empty_tutor.setVisibility(View.VISIBLE);
                                return;
                            }
                        }
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Mondaylist, ReserveSpotActivity.this.recycler_available_slot_moday, "Monday");
                    } else {
                        ReserveSpotActivity.this.Mondaylist.add("Tutor not available.");
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Mondaylist, ReserveSpotActivity.this.recycler_available_slot_moday, "Monday");
                    }
                    String Tuesday = finallyPr.getResult().getTuesday();
                    JSONArray jsonTuesday = null;
                    try {
                        jsonTuesday = new JSONArray(Tuesday);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!Tuesday.equalsIgnoreCase("[]")) {
                        for (int i2 = 0; i2 < jsonTuesday.length(); i2++) {
                            try {
                                ReserveSpotActivity.this.Tuesdaylist.add(jsonTuesday.get(i2).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Tuesdaylist, ReserveSpotActivity.this.recycler_available_slot_tuesday, "Tuesday");
                    } else {
                        ReserveSpotActivity.this.Tuesdaylist.add("Tutor not available.");
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Tuesdaylist, ReserveSpotActivity.this.recycler_available_slot_tuesday, "Tuesday");
                    }
                    String wednesDay = finallyPr.getResult().getWednesday();
                    JSONArray jsonArraywednesDay = null;
                    try {
                        jsonArraywednesDay = new JSONArray(wednesDay);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!wednesDay.equalsIgnoreCase("[]")) {
                        for (int i3 = 0; i3 < jsonArraywednesDay.length(); i3++) {
                            try {
                                ReserveSpotActivity.this.wednesDaylist.add(jsonArraywednesDay.get(i3).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.wednesDaylist, ReserveSpotActivity.this.recycler_available_slot_wednesDay, "wednesDay");
                    } else {
                        ReserveSpotActivity.this.wednesDaylist.add("Tutor not available.");
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.wednesDaylist, ReserveSpotActivity.this.recycler_available_slot_wednesDay, "wednesDay");
                    }
                    String ThrusDay = finallyPr.getResult().getThursday();
                    JSONArray jsonArrayThrusDay = null;
                    try {
                        jsonArrayThrusDay = new JSONArray(ThrusDay);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!ThrusDay.equalsIgnoreCase("[]")) {
                        int i4 = 0;
                        while (true) {
                            String ThrusDay2 = ThrusDay;
                            if (i4 >= jsonArrayThrusDay.length()) {
                                break;
                            }
                            try {
                                ReserveSpotActivity.this.ThrusDaylist.add(jsonArrayThrusDay.get(i4).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            i4++;
                            ThrusDay = ThrusDay2;
                            status = status;
                        }
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.ThrusDaylist, ReserveSpotActivity.this.recycler_available_slot_thursday, "ThrusDay");
                    } else {
                        String str = status;
                        ReserveSpotActivity.this.ThrusDaylist.add("Tutor not available.");
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.ThrusDaylist, ReserveSpotActivity.this.recycler_available_slot_thursday, "ThrusDay");
                    }
                    String Friday = finallyPr.getResult().getFriday();
                    JSONArray jsonArrayFriday = null;
                    try {
                        jsonArrayFriday = new JSONArray(Friday);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!Friday.equalsIgnoreCase("[]")) {
                        int i5 = 0;
                        while (true) {
                            String Friday2 = Friday;
                            if (i5 >= jsonArrayFriday.length()) {
                                break;
                            }
                            try {
                                ReserveSpotActivity.this.Fridaylist.add(jsonArrayFriday.get(i5).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            i5++;
                            Friday = Friday2;
                            jsonArrayFriday = jsonArrayFriday;
                        }
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Fridaylist, ReserveSpotActivity.this.recycler_available_slot_friday, "Friday");
                    } else {
                        JSONArray jSONArray = jsonArrayFriday;
                        ReserveSpotActivity.this.Fridaylist.add("Tutor not available.");
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Fridaylist, ReserveSpotActivity.this.recycler_available_slot_friday, "Friday");
                    }
                    String saturday = finallyPr.getResult().getSaturday();
                    JSONArray jsonArraySaturday = null;
                    try {
                        jsonArraySaturday = new JSONArray(saturday);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!saturday.equalsIgnoreCase("[]")) {
                        int i6 = 0;
                        while (true) {
                            String saturday2 = saturday;
                            if (i6 >= jsonArraySaturday.length()) {
                                break;
                            }
                            try {
                                ReserveSpotActivity.this.Saturdaylist.add(jsonArraySaturday.get(i6).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            i6++;
                            saturday = saturday2;
                            jsonArraySaturday = jsonArraySaturday;
                        }
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Saturdaylist, ReserveSpotActivity.this.recycler_available_slot_saturday, "saturday");
                    } else {
                        JSONArray jSONArray2 = jsonArraySaturday;
                        ReserveSpotActivity.this.Saturdaylist.add("Tutor not available.");
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Saturdaylist, ReserveSpotActivity.this.recycler_available_slot_saturday, "saturday");
                    }
                    String sunday = finallyPr.getResult().getSaturday();
                    JSONArray jsonArraySunday = null;
                    try {
                        jsonArraySunday = new JSONArray(sunday);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    if (!sunday.equalsIgnoreCase("[]")) {
                        for (int i7 = 0; i7 < jsonArraySunday.length(); i7++) {
                            try {
                                ReserveSpotActivity.this.Sunday1list.add(jsonArraySunday.get(i7).toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Sunday1list, ReserveSpotActivity.this.recycler_available_slot_sunday, "sunday");
                    } else {
                        ReserveSpotActivity.this.Sunday1list.add("Tutor not available.");
                        ReserveSpotActivity.this.setAdapterDay(ReserveSpotActivity.this.Sunday1list, ReserveSpotActivity.this.recycler_available_slot_sunday, "sunday");
                    }
                    Toast.makeText(ReserveSpotActivity.this, message, Toast.LENGTH_LONG).show();
                    return;
                }
                ReserveSpotActivity.this.progressBar.setVisibility(View.GONE);
                ReserveSpotActivity.this.txt_empty_tutor.setVisibility(View.VISIBLE);
            }

            public void onFailure(Call<GteTutorTime> call, Throwable t) {
                ReserveSpotActivity.this.progressBar.setVisibility(View.GONE);
                ReserveSpotActivity.this.txt_empty_tutor.setVisibility(View.VISIBLE);
            }
        });
    }

    private String[] ChnageArra(String line) {
        System.out.println(Arrays.toString(line.split(" ")));
        return Pattern.compile(" ").split(line);
    }

    public void listClick(int position, String type, String Remove) {
        if (type.equals("Monday")) {
            if (!Remove.equals("remove")) {
                monday.put(this.Mondaylist.get(position));
            } else {
                monday.remove(position);
            }
            Log.d("mondayTime :", "mondayTime =" + monday);
        } else if (type.equals("Tuesday")) {
            if (!Remove.equals("remove")) {
                tuesday.put(this.Tuesdaylist.get(position));
            } else {
                tuesday.remove(position);
            }
            Log.d("Tuesday :", "Tuesday =" + tuesday);
        } else if (type.equals("wednesDay")) {
            if (!Remove.equals("remove")) {
                wednesday.put(this.wednesDaylist.get(position));
            } else {
                wednesday.remove(position);
            }
            Log.d("wednesday :", "wednesday =" + wednesday);
        } else if (type.equals("ThrusDay")) {
            if (!Remove.equals("remove")) {
                thursday.put(this.ThrusDaylist.get(position));
            } else {
                thursday.remove(position);
            }
            Log.d("thursday :", "thursday =" + thursday);
        } else if (type.equals("Friday")) {
            if (!Remove.equals("remove")) {
                friday.put(this.Fridaylist.get(position));
            } else {
                friday.remove(position);
            }
            Log.d("friday :", "friday =" + friday);
        } else if (type.equals("saturday")) {
            if (!Remove.equals("remove")) {
                saturday.put(this.Saturdaylist.get(position));
            } else {
                saturday.remove(position);
            }
            Log.d("saturday :", "saturday =" + saturday);
        } else if (type.equals("sunday")) {
            if (!Remove.equals("remove")) {
                sunday.put(this.Sunday1list.get(position));
            } else {
                sunday.remove(position);
            }
            Log.d("sunday :", "sunday =" + sunday);
        }
    }
}
