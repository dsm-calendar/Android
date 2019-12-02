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
import com.example.dsm_calendar.contract.GroupScheduleContract;
import com.example.dsm_calendar.data.GroupScheduleRepository;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.Singleton.BusProvider;
import com.example.dsm_calendar.presenter.GroupSchedulePresenter;
import com.example.dsm_calendar.ui.Decorator.OnDayDecorator;
import com.example.dsm_calendar.ui.Decorator.SaturdayDecorator;
import com.example.dsm_calendar.ui.Decorator.Schedule1Decorator;
import com.example.dsm_calendar.ui.Decorator.Schedule2Decorator;
import com.example.dsm_calendar.ui.Decorator.ScheduleDecorator;
import com.example.dsm_calendar.ui.Decorator.SundayDecorator;
import com.example.dsm_calendar.ui.adapter.GroupScheduleRVAdapter;
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

public class GroupScheduleActivity extends AppCompatActivity implements GroupScheduleContract.View {

    private ImageButton backButton;
    private MaterialCalendarView calendarView;
    private TextView noListTextView;
    private RecyclerView recyclerView;
    private ImageButton addButton;
    private GroupScheduleRVAdapter adapter;

    private GroupSchedulePresenter presenter = new GroupSchedulePresenter(this, new GroupScheduleRepository(this));
    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<Schedule> todayList = new ArrayList<>();

    private ArrayList<CalendarDay> schedules0 = new ArrayList<>();
    private ArrayList<CalendarDay> schedules1 = new ArrayList<>();
    private ArrayList<CalendarDay> schedules2 = new ArrayList<>();
    private int roomId;
    private int groupCalendarId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_schedule);
        BusProvider.getInstance().register(this);

        Intent roomIntent = getIntent();
        roomId = roomIntent.getIntExtra("roomId", -1);
        groupCalendarId = roomIntent.getIntExtra("groupCalendarId", -1);

        presenter.onStarted(roomId);

        backButton = findViewById(R.id.button_group_schedule_back);
        calendarView = findViewById(R.id.cv_group_calendar);
        noListTextView = findViewById(R.id.tv_no_list_group_schedule);
        recyclerView = findViewById(R.id.rv_group_schedule);
        addButton = findViewById(R.id.button_group_schedule_add);

        adapter = new GroupScheduleRVAdapter(presenter, this);

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
            adapter.schedules.clear();
            adapter.schedules.addAll(todayList);
            adapter.notifyDataSetChanged();
            checkList();
        });

        backButton.setOnClickListener(v -> finish());
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddScheduleActivity.class);
            intent.putExtra("schedule code", "group");
            intent.putExtra("groupCalendarId", groupCalendarId);
            startActivity(intent);
        });

//        calendarView.setSelectedDate(new Date());
        checkList();
    }

    @Override
    protected void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void getNewScheduleList(ScheduleEvent event){
        if (event.getStatus() == ScheduleEvent.SCHEDULE_EVENT.SCHEDULE_ADD){
            presenter.onStarted(roomId);
        }
    }

    private void refreshScheduleDecorators() {
        calendarView.addDecorators(new ScheduleDecorator(schedules0, this));
        calendarView.addDecorators(new Schedule1Decorator(schedules1, this));
        calendarView.addDecorators(new Schedule2Decorator(schedules2, this));
    }

    private void checkList(){
        if (adapter.getItemCount() == 0){
            noListTextView.setVisibility(View.VISIBLE);
        } else {
            noListTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessageForDeleteSuccess() {
        Toast.makeText(this, "delete Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForDeleteFail(String message) {
        Toast.makeText(this, "delete Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForGetScheduleFail(String message) {
        Toast.makeText(this, "loading Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getList(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
        setScheduleCount();
        refreshScheduleDecorators();
        checkList();
    }

    @Override
    public void deleteSchedule(int position) {
        for (int i = 0; i < schedules.size(); ++i){
            if (schedules.get(i) == adapter.schedules.get(position)){
                schedules.remove(i--);
            }
        }
        adapter.schedules.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
        setScheduleCount();
        refreshScheduleDecorators();
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
