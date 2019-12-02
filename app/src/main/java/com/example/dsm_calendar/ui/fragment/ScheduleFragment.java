package com.example.dsm_calendar.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.ScheduleFragmentContract;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.ScheduleFragmentRepository;
import com.example.dsm_calendar.data.Singleton.BusProvider;
import com.example.dsm_calendar.presenter.ScheduleFragmentPresenter;
import com.example.dsm_calendar.ui.Decorator.OnDayDecorator;
import com.example.dsm_calendar.ui.Decorator.SaturdayDecorator;
import com.example.dsm_calendar.ui.Decorator.Schedule1Decorator;
import com.example.dsm_calendar.ui.Decorator.Schedule2Decorator;
import com.example.dsm_calendar.ui.Decorator.ScheduleDecorator;
import com.example.dsm_calendar.ui.Decorator.SundayDecorator;
import com.example.dsm_calendar.ui.activity.AddScheduleActivity;
import com.example.dsm_calendar.ui.adapter.ScheduleFragmentRVAdapter;
import com.example.dsm_calendar.util.ScheduleEvent;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ScheduleFragment extends Fragment implements ScheduleFragmentContract.View {

    private TextView noListTextView;
    private RecyclerView recyclerView;
    private ScheduleFragmentRVAdapter adapter;
    private ImageButton scheduleAddButton;
    private MaterialCalendarView calendarView;
    private ScheduleFragmentPresenter scheduleFragmentPresenter = new ScheduleFragmentPresenter(this, new ScheduleFragmentRepository(getActivity()));

    private ArrayList<Schedule> schedules = new ArrayList<>();
    private ArrayList<Schedule> todayList = new ArrayList<>();

    private ArrayList<CalendarDay> schedules0 = new ArrayList<>();
    private ArrayList<CalendarDay> schedules1 = new ArrayList<>();
    private ArrayList<CalendarDay> schedules2 = new ArrayList<>();

    public ScheduleFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_schedule, container, false);

        BusProvider.getInstance().register(this);
        scheduleFragmentPresenter.onStarted();
        setScheduleCount();

        noListTextView = rootView.findViewById(R.id.tv_no_list_my_schedule);

        recyclerView = rootView.findViewById(R.id.rv_schedule_schedule);
        adapter = new ScheduleFragmentRVAdapter(getActivity(), scheduleFragmentPresenter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        calendarView = rootView.findViewById(R.id.cv_schedule_calendar);
        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator(),
                new OnDayDecorator(),
                new ScheduleDecorator(schedules0, getActivity()),
                new Schedule1Decorator(schedules1, getActivity()),
                new Schedule2Decorator(schedules2, getActivity()));

        calendarView.setOnDateChangedListener((widget, date, selected) -> {
            todayList.clear();
            for (Schedule schedule : schedules) {
                if (date.isInRange(schedule.getStartDay(), schedule.getEndDay()))
                    todayList.add(schedule);
            }
            adapter.list.clear();
            adapter.list.addAll(todayList);
            adapter.notifyDataSetChanged();
            checkList();
        });

        scheduleAddButton = rootView.findViewById(R.id.button_schedule_add);
        scheduleAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddScheduleActivity.class);
            intent.putExtra("schedule code", "private");
            startActivity(intent);
        });

        checkList();
        return rootView;
    }

    @Override
    public void onDestroy() {
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

    private void refreshScheduleDecorators() {
        calendarView.addDecorators(new ScheduleDecorator(schedules0, getActivity()));
        calendarView.addDecorators(new Schedule1Decorator(schedules1, getActivity()));
        calendarView.addDecorators(new Schedule2Decorator(schedules2, getActivity()));
    }

    @Subscribe
    public void getNewScheduleList(ScheduleEvent status) {
        if (status.getStatus() == ScheduleEvent.SCHEDULE_EVENT.SCHEDULE_ADD) {
            scheduleFragmentPresenter.onStarted();
        }
    }

    @Override
    public void showMessageForDeleteSuccess() {
        Toast.makeText(getActivity(), "delete", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForDeleteFail(String message) {
        Toast.makeText(getActivity(), "delete fail\nmessage: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForGetScheduleFail(String message) {
        Toast.makeText(getActivity(), "loading fail\nmessage: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getItems(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
        setScheduleCount();
        refreshScheduleDecorators();
        checkList();
    }

    @Override
    public void deleteSchedule(int position) {
        for (int i = 0; i < schedules.size(); ++i){
            if (schedules.get(i) == adapter.list.get(position)){
                schedules.remove(i--);
            }
        }
        adapter.list = schedules;
        adapter.notifyDataSetChanged();
        setScheduleCount();
        refreshScheduleDecorators();
    }

    private void setScheduleCount() {
        if (schedules.size() == 0) return;

        ArrayList<Schedule> sortedSchedules = new ArrayList<>(schedules);
        Collections.sort(sortedSchedules);

        Date minDay = sortedSchedules.get(0).getStartDay().getDate();
        Date maxDay = sortedSchedules.get(0).getEndDay().getDate();

        for (int i = 1; i < sortedSchedules.size(); ++i) {
            Date endDay = sortedSchedules.get(i).getEndDay().getDate();
            maxDay = endDay.after(maxDay) ? endDay : maxDay;
        }

        int size = getDiffFromDay(maxDay, minDay) + 1;

        ArrayList<Integer> contains = new ArrayList<>(size);
        while (--size >= 0) contains.add(0);

        for (Schedule schedule : sortedSchedules) {
            Date endDay = schedule.getEndDay().getDate();
            Calendar calendar = Calendar.getInstance();

            for (calendar.setTime(schedule.getStartDay().getDate()); !calendar.getTime().after(endDay); calendar.add(Calendar.DATE, 1)) {
                int idx = getDiffFromDay(calendar.getTime(), minDay);
                int oldValue = contains.get(idx);
                contains.set(idx, oldValue + 1);
            }
        }

        schedules0.clear();
        schedules1.clear();
        schedules2.clear();

        for (int i = 0; i < contains.size(); ++i) {
            if (contains.get(i) == 0) continue;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(minDay);
            calendar.add(Calendar.DATE, i);

            switch (Math.min(contains.get(i) - 1, 2)) {
                case 0: schedules0.add(CalendarDay.from(calendar.getTime())); break;
                case 1: schedules1.add(CalendarDay.from(calendar.getTime())); break;
                case 2: schedules2.add(CalendarDay.from(calendar.getTime())); break;
            }
        }
    }

    private int getDiffFromDay(Date lhs, Date rhs) {
        long diff = lhs.getTime() - rhs.getTime();
        return (int)TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}
