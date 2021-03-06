package com.tech.twestudentside.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.utils.DateUtils;
import com.tech.twestudentside.R;
import com.tech.twestudentside.activity.AttendanceActivity;
import com.tech.twestudentside.listner.FragmentListener;
import com.tech.twestudentside.utils.DrawableUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class DailyAttendanceFragment extends Fragment {


    FragmentListener listener;
    Context mcontext=getActivity();

    public DailyAttendanceFragment(FragmentListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_daily_attendance, container, false);


        List<EventDay> events = new ArrayList<>();


        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, DrawableUtils.getCircleDrawableWithText(getActivity(), "M")));


        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 10);
        events.add(new EventDay(calendar1,R.drawable.sample_circle));



        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 12);
        events.add(new EventDay(calendar2,R.drawable.sample_circle));




        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DAY_OF_MONTH, 13);
        events.add(new EventDay(calendar3,R.drawable.sample_circle));



        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, 14);
        events.add(new EventDay(calendar4,R.drawable.sample_circle));





        Calendar calendar9 = Calendar.getInstance();
        calendar9.add(Calendar.DAY_OF_MONTH, 15);
        events.add(new EventDay(calendar9,R.drawable.sample_greencircle));



        Calendar calendar10 = Calendar.getInstance();
        calendar10.add(Calendar.DAY_OF_MONTH, 16);
        events.add(new EventDay(calendar10,R.drawable.sample_greencircle));




        Calendar calendar11 = Calendar.getInstance();
        calendar11.add(Calendar.DAY_OF_MONTH, 17);
        events.add(new EventDay(calendar11,R.drawable.sample_greencircle));



        Calendar calendar12 = Calendar.getInstance();
        calendar12.add(Calendar.DAY_OF_MONTH, 19);

        events.add(new EventDay(calendar12,R.drawable.sample_greencircle));






        Calendar calendar5 = Calendar.getInstance();
        calendar5.add(Calendar.DAY_OF_MONTH, 4);
        events.add(new EventDay(calendar5,R.drawable.sample_graycircle));

        Calendar calendar6 = Calendar.getInstance();
        calendar6.add(Calendar.DAY_OF_MONTH, 11);
        events.add(new EventDay(calendar6,R.drawable.sample_graycircle));

        Calendar calendar7 = Calendar.getInstance();
        calendar7.add(Calendar.DAY_OF_MONTH, 18);
        events.add(new EventDay(calendar7,R.drawable.sample_graycircle));

        Calendar calendar8 = Calendar.getInstance();
        calendar8.add(Calendar.DAY_OF_MONTH, 25);
        events.add(new EventDay(calendar8,R.drawable.sample_graycircle));


        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);

        Calendar min = Calendar.getInstance();
        min.add(Calendar.MONTH, -2);

        Calendar max = Calendar.getInstance();
        max.add(Calendar.MONTH, 2);

        calendarView.setMinimumDate(min);
        calendarView.setMaximumDate(max);

        calendarView.setEvents(events);

        //calendarView.setDisabledDays(getDisabledDays());

        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(mcontext, eventDay.getCalendar().getTime().toString() + " " + eventDay.isEnabled(),
                        Toast.LENGTH_SHORT).show());

       /* Button setDateButton = (Button) findViewById(R.id.setDateButton);
        setDateButton.setOnClickListener(v -> {
            try {
                Calendar randomCalendar = getRandomCalendar();
                String text = randomCalendar.getTime().toString();
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                calendarView.setDate(randomCalendar);
            } catch (OutOfDateRangeException exception) {
                exception.printStackTrace();

                Toast.makeText(getApplicationContext(),
                        "Date is out of range",
                        Toast.LENGTH_LONG).show();
            }
        });
*/

        return view;


    }


    private List<Calendar> getDisabledDays() {
        Calendar firstDisabled = DateUtils.getCalendar();
        firstDisabled.add(Calendar.DAY_OF_MONTH, 2);

        Calendar secondDisabled = DateUtils.getCalendar();
        secondDisabled.add(Calendar.DAY_OF_MONTH, 1);

        Calendar thirdDisabled = DateUtils.getCalendar();
        thirdDisabled.add(Calendar.DAY_OF_MONTH, 18);

        List<Calendar> calendars = new ArrayList<>();
        calendars.add(firstDisabled);
        calendars.add(secondDisabled);
        calendars.add(thirdDisabled);
        return calendars;
    }

    private Calendar getRandomCalendar() {
        Random random = new Random();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, random.nextInt(99));

        return calendar;
    }



}