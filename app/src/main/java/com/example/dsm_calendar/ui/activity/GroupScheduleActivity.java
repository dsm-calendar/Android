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
import com.example.dsm_calendar.ui.Decorator.ScheduleDecorator;
import com.example.dsm_calendar.ui.Decorator.SundayDecorator;
import com.example.dsm_calendar.ui.adapter.GroupScheduleRVAdapter;
import com.example.dsm_calendar.util.ScheduleEvent;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.Date;
import java.util.TreeSet;

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
                new ScheduleDecorator(new TreeSet<>(schedules), this));

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

        calendarView.setSelectedDate(new Date());
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
    }

    @Override
    public void deleteSchedule(int position) {
        adapter.schedules.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, adapter.getItemCount());
    }
}
