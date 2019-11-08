package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.ui.adapter.SchoolScheduleAdapter;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class SchoolScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton offButton;
    private MaterialCalendarView calendarView;
    private RecyclerView recyclerView;
    private ImageButton addSchedule;

    private SchoolScheduleAdapter adapter;

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

        adapter = new SchoolScheduleAdapter();
        adapter.scheduleList.add(new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1)));
        adapter.scheduleList.add(new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1)));
        adapter.scheduleList.add(new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1)));
        adapter.scheduleList.add(new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1)));
        adapter.scheduleList.add(new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1)));
        adapter.scheduleList.add(new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1)));
        adapter.scheduleList.add(new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1)));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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
