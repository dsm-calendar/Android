package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class GroupActivity extends AppCompatActivity {

    private TextView groupTitle;
    private ImageButton showSchedule;
    private ImageButton showMember;
    private ImageView groupImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        groupTitle = findViewById(R.id.tv_group_name);
        showSchedule = findViewById(R.id.button_group_schedule);
        showMember = findViewById(R.id.button_group_member);
        groupImage = findViewById(R.id.iv_group_image);
    }
}
