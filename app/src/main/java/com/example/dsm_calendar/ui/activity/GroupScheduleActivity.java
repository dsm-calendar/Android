package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;
import com.google.android.material.textfield.TextInputLayout;

public class GroupScheduleActivity extends AppCompatActivity {

    private TextView noListTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_schedule);

        noListTextView = findViewById(R.id.tv_no_list_group_schedule);
        //TODO: add decorator to calendar
    }

    private void checkList(){
        //TODO: add adapter and set visibility;
    }
}
