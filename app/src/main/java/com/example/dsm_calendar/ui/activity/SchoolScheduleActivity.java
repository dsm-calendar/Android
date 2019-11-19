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
import com.example.dsm_calendar.presenter.SchoolSchedulePresenter;
import com.example.dsm_calendar.ui.Decorator.OnDayDecorator;
import com.example.dsm_calendar.ui.Decorator.SaturdayDecorator;
import com.example.dsm_calendar.ui.Decorator.ScheduleDecorator;
import com.example.dsm_calendar.ui.Decorator.SundayDecorator;
import com.example.dsm_calendar.ui.adapter.SchoolScheduleAdapter;
import com.example.dsm_calendar.util.ScheduleEvent;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.TreeSet;

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

        offButton.setOnClickListener(this);
        addSchedule.setOnClickListener(this);

        adapter = new SchoolScheduleAdapter(presenter);
        presenter.onStarted();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        calendarView.addDecorators(
                new SaturdayDecorator(),
                new SundayDecorator(),
                new OnDayDecorator(),
                new ScheduleDecorator(new TreeSet<>(schedules), this));
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
//        addSchedule.setVisibility(View.GONE);
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

    @Subscribe
    public void getNewScheduleList(ScheduleEvent status) {
        if (status.getStatus() == ScheduleEvent.EVENT.SCHEDULE_ADD) {
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
        schedules = list;
    }

    @Override
    public void deleteSchedule(int position) {
        adapter.scheduleList.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }
}
