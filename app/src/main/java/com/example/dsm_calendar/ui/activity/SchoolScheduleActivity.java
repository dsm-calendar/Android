package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.SchoolScheduleContract;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.SchoolScheduleRepository;
import com.example.dsm_calendar.data.Singleton.BusProvider;
import com.example.dsm_calendar.data.Singleton.UserPreference;
import com.example.dsm_calendar.presenter.SchoolSchedulePresenter;
import com.example.dsm_calendar.ui.Decorator.OnDayDecorator;
import com.example.dsm_calendar.ui.Decorator.SaturdayDecorator;
import com.example.dsm_calendar.ui.Decorator.Schedule1Decorator;
import com.example.dsm_calendar.ui.Decorator.Schedule2Decorator;
import com.example.dsm_calendar.ui.Decorator.ScheduleDecorator;
import com.example.dsm_calendar.ui.Decorator.SundayDecorator;
import com.example.dsm_calendar.ui.adapter.SchoolScheduleAdapter;
import com.example.dsm_calendar.util.ScheduleEvent;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class SchoolScheduleActivity extends AppCompatActivity implements SchoolScheduleContract.View, View.OnClickListener {

    private ImageButton offButton;
    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private TextView noListTextView;
    private ImageButton addSchedule;

    private SchoolScheduleAdapter adapter;
    private SchoolSchedulePresenter presenter = new SchoolSchedulePresenter(this, new SchoolScheduleRepository(this));

    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<Schedule> todayList = new ArrayList<>();

    private ArrayList<CalendarDay> schedules0 = new ArrayList<>();
    private ArrayList<CalendarDay> schedules1 = new ArrayList<>();
    private ArrayList<CalendarDay> schedules2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolcalendar);
        BusProvider.getInstance().register(this);
        presenter.onStarted();

        offButton = findViewById(R.id.button_school_schedule_back);
        calendarView = findViewById(R.id.cv_school_calendar);
        recyclerView = findViewById(R.id.rv_school_schedule);
        noListTextView = findViewById(R.id.tv_no_list_school_schedule);
        addSchedule = findViewById(R.id.button_school_schedule_add);

        adminMode(UserPreference.getInstance(this).getIsAdmin());

        offButton.setOnClickListener(this);
        addSchedule.setOnClickListener(this);

        adapter = new SchoolScheduleAdapter(presenter, UserPreference.getInstance(this).getIsAdmin());
        presenter.onStarted();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator(),
                new OnDayDecorator(),
                new ScheduleDecorator(schedules0, this),
                new Schedule1Decorator(schedules1, this),
                new Schedule2Decorator(schedules2, this));

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            todayList.clear();
            for (Schedule schedule : schedules){
                if (date.isInRange(schedule.getStartDay(), schedule.getEndDay()))
                    todayList.add(schedule);
            }
            adapter.scheduleList.clear();
            adapter.scheduleList.addAll(todayList);
            adapter.notifyDataSetChanged();
            checkList();
        });

        checkList();
    }

    @Override
    protected void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    private void checkList() {
        if (adapter.getItemCount() == 0) {
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_school_schedule_back:
                finish();
                break;
            case R.id.button_school_schedule_add:
                Intent intent = new Intent(this, AddScheduleActivity.class);
                intent.putExtra("schedule code", "school");
                startActivity(intent);
                break;
        }
    }

    private void refreshScheduleDecorators() {
        calendarView.addDecorators(new ScheduleDecorator(schedules0, this));
        calendarView.addDecorators(new Schedule1Decorator(schedules1, this));
        calendarView.addDecorators(new Schedule2Decorator(schedules2, this));
    }

    @Subscribe
    public void getNewScheduleList(ScheduleEvent status) {
        if (status.getStatus() == ScheduleEvent.SCHEDULE_EVENT.SCHEDULE_ADD) {
            presenter.onStarted();
        }
        Toast.makeText(this, "Event Bus", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForDeleteSuccess() {
        Toast.makeText(this, "Delete success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForDeleteFail(String message) {
        Toast.makeText(this, "Delete Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForGetScheduleFail(String message) {
        Toast.makeText(this, "Loading schedule Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getItems(ArrayList<Schedule> list) {
        this.schedules = list;
        setScheduleCount();
        refreshScheduleDecorators();
        checkList();
    }

    @Override
    public void deleteSchedule(int position) {
        for (int i = 0; i < schedules.size(); ++i){
            if (schedules.get(i) == adapter.scheduleList.get(position)){
                schedules.remove(i--);
            }
        }
        adapter.scheduleList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
        setScheduleCount();
        refreshScheduleDecorators();
    }

    private void adminMode(boolean isAdmin){
        if (isAdmin){
            addSchedule.setVisibility(View.VISIBLE);
        } else {
            addSchedule.setVisibility(View.GONE);
        }
    }

    private void setScheduleCount() {
        ArrayList<Schedule> sortedSchedules = new ArrayList<>(schedules);
        Collections.sort(sortedSchedules);

        Date minDay = new Date();
        Date maxDay = new Date();

        if (sortedSchedules.size() != 0){
            minDay = sortedSchedules.get(0).getStartDay().getDate();
            maxDay = sortedSchedules.get(0).getEndDay().getDate();
        }

        for (int i = 1; i < sortedSchedules.size(); ++i) {
            Date endDay = sortedSchedules.get(i).getEndDay().getDate();
            maxDay = endDay.after(maxDay) ? endDay : maxDay;
        }

        int size = getDiffFromDay(maxDay, minDay);

        ArrayList<Integer> contains = new ArrayList<>(size);
        while (--size >= 0) contains.add(0);

        for (Schedule schedule : sortedSchedules) {
            Date endDay = schedule.getEndDay().getDate();
            Calendar calendar = Calendar.getInstance();

            for (calendar.setTime(schedule.getStartDay().getDate()); !calendar.getTime().equals(endDay); calendar.add(Calendar.DATE, 1)) {
                int idx = getDiffFromDay(calendar.getTime(), minDay);
                int oldValue = contains.get(idx);
                contains.set(idx, oldValue + 1);
            }
        }

        ArrayList<CalendarDay>[] scheduleList = (ArrayList<CalendarDay>[])new ArrayList[3];
        scheduleList[0] = schedules0;
        scheduleList[1] = schedules1;
        scheduleList[2] = schedules2;

        for (int i = 0; i < contains.size(); ++i) {
            if (contains.get(i) == 0) continue;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(minDay);
            calendar.add(Calendar.DATE, i);

            ArrayList<CalendarDay> mySchedule = scheduleList[Math.min(contains.get(i) - 1, 2)];
            mySchedule.add(CalendarDay.from(calendar.getTime()));
        }
    }

    private int getDiffFromDay(Date lhs, Date rhs) {
        long diff = lhs.getTime() - rhs.getTime();
        return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
