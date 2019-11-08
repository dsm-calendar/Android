package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.SchoolScheduleContract;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.SchoolScheduleRepository;
import com.example.dsm_calendar.presenter.SchoolSchedulePresenter;
import com.example.dsm_calendar.ui.adapter.SchoolScheduleAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.ArrayList;

public class SchoolScheduleActivity extends AppCompatActivity implements SchoolScheduleContract.View, View.OnClickListener {

    private ImageButton offButton;
    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private ImageButton addSchedule;

    private SchoolScheduleAdapter adapter;
    private SchoolSchedulePresenter presenter = new SchoolSchedulePresenter(this, new SchoolScheduleRepository());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schoolcalendar);

        offButton = findViewById(R.id.button_school_schedule_back);
        calendarView = findViewById(R.id.cv_school_calendar);
        recyclerView = findViewById(R.id.rv_school_schedule);
        addSchedule = findViewById(R.id.button_school_schedule_add);

        offButton.setOnClickListener(this);
        addSchedule.setOnClickListener(this);

        adapter = new SchoolScheduleAdapter(presenter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        addSchedule.setVisibility(View.GONE);
        presenter.onStarted();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_school_schedule_back:
                finish();
                break;
            case R.id.button_school_schedule_add:
                Intent intent = new Intent(this, AddScheduleActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void showMessageForAddSuccess() {
        Toast.makeText(this, "Add success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForAddFail(String message) {
        Toast.makeText(this, "Add Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
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
    public void getItems(ArrayList<SampleSchedule> list) {
        adapter.scheduleList = list;
    }
}
