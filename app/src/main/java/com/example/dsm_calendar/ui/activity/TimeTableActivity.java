package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class TimeTableActivity extends AppCompatActivity {

    private ImageButton timeTableOff;
    private ImageButton timeTableEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        timeTableOff = findViewById(R.id.button_timetable_off);
        timeTableOff.setOnClickListener(v -> finish());
    }
}
