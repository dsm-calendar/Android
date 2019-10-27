package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dsm_calendar.R;

public class AddScheduleActivity extends AppCompatActivity {

    private ImageButton addScheduleOff;
    private EditText title;
    private EditText content;
    private ConstraintLayout startDay;
    private ConstraintLayout endDay;
    private Button cancel;
    private Button confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);

        addScheduleOff = findViewById(R.id.button_addschedule_off);
        title = findViewById(R.id.et_addschedule_title);
        content = findViewById(R.id.et_addschedule_content);
        startDay = findViewById(R.id.cl_addschedule_startday);
        endDay = findViewById(R.id.cl_addschedule_endday);
        cancel = findViewById(R.id.button_addschedule_cancel);
        confirm = findViewById(R.id.button_addschedule_confirm);
    }
}
