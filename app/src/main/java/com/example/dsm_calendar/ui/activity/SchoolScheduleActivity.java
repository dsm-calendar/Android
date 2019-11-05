package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class SchoolScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton offButton;
    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private ImageButton addSchedule;

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

        addSchedule.setVisibility(View.GONE);
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
}
