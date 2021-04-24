package com.tech.twestudentside.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.tech.twestudentside.R;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.utils.DrawableUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MonthlyAttendanceFragment extends Fragment {
    FragmentListener listener;
    Context mcontext = getActivity();

    public MonthlyAttendanceFragment(FragmentListener listener2) {
        this.listener = listener2;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monthly_attendance, container, false);
        ArrayList arrayList = new ArrayList();
        Calendar calendar = Calendar.getInstance();
        arrayList.add(new EventDay(calendar, DrawableUtils.getCircleDrawableWithText(getActivity(), "O")));
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(5, 10);
        arrayList.add(new EventDay(calendar1, (int) R.drawable.sample_circle));
        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(5, 12);
        arrayList.add(new EventDay(calendar2, (int) R.drawable.sample_circle));
        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(5, 13);
        arrayList.add(new EventDay(calendar3, (int) R.drawable.sample_circle));
        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(5, 14);
        arrayList.add(new EventDay(calendar4, (int) R.drawable.sample_circle));
        Calendar calendar9 = Calendar.getInstance();
        calendar9.add(5, 15);
        arrayList.add(new EventDay(calendar9, (int) R.drawable.sample_greencircle));
        Calendar calendar10 = Calendar.getInstance();
        calendar10.add(5, 16);
        arrayList.add(new EventDay(calendar10, (int) R.drawable.sample_greencircle));
        Calendar calendar11 = Calendar.getInstance();
        calendar11.add(5, 17);
        arrayList.add(new EventDay(calendar11, (int) R.drawable.sample_greencircle));
        Calendar calendar12 = Calendar.getInstance();
        calendar12.add(5, 19);
        arrayList.add(new EventDay(calendar12, (int) R.drawable.sample_greencircle));
        Calendar calendar5 = Calendar.getInstance();
        calendar5.add(5, 4);
        arrayList.add(new EventDay(calendar5, (int) R.drawable.sample_graycircle));
        Calendar calendar6 = Calendar.getInstance();
        calendar6.add(5, 11);
        arrayList.add(new EventDay(calendar6, (int) R.drawable.sample_graycircle));
        Calendar calendar7 = Calendar.getInstance();
        calendar7.add(5, 18);
        arrayList.add(new EventDay(calendar7, (int) R.drawable.sample_graycircle));
        Calendar calendar8 = Calendar.getInstance();
        Calendar calendar13 = calendar;
        calendar8.add(5, 25);
        arrayList.add(new EventDay(calendar8, (int) R.drawable.sample_graycircle));
        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarViewM);
        Calendar min = Calendar.getInstance();
        Calendar calendar14 = calendar8;
        Calendar calendar15 = calendar1;
        min.add(2, -2);
        Calendar max = Calendar.getInstance();
        max.add(2, 2);
        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);
        calendarView.setEvents(arrayList);
        ArrayList arrayList2 = arrayList;
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            public final void onDayClick(EventDay eventDay) {
                MonthlyAttendanceFragment.this.lambda$onCreateView$0$MonthlyAttendanceFragment(eventDay);
            }
        });
        return view;
    }

    public /* synthetic */ void lambda$onCreateView$0$MonthlyAttendanceFragment(EventDay eventDay) {
        Context context = this.mcontext;
        Toast.makeText(context, eventDay.getCalendar().getTime().toString() + " " + eventDay.isEnabled(), Toast.LENGTH_LONG).show();
    }

    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(5, 2);
        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(5, 1);
        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(5, 18);
        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private Calendar getRandomCalendar() {
        Random random = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, random.nextInt(99));
        return calendar;
    }
}
