package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.adapter.ScheduleFragmentRVAdapter;
import com.google.android.material.textfield.TextInputLayout;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class GroupScheduleActivity extends AppCompatActivity {

    private ImageButton backButton;
    private MaterialCalendarView calendarView;
    private TextView noListTextView;
    private RecyclerView recyclerView;
    private ImageButton addButton;
    private ScheduleFragmentRVAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_schedule);

        backButton = findViewById(R.id.button_group_schedule_back);
        calendarView = findViewById(R.id.cv_group_calendar);
        noListTextView = findViewById(R.id.tv_no_list_group_schedule);
        recyclerView = findViewById(R.id.rv_group_schedule);
        addButton = findViewById(R.id.button_group_schedule_add);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        //TODO: add decorator to calendar
    }

    private void checkList(){
        //TODO: add adapter and set visibility;
    }
}
