package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class GroupSingleActivity extends AppCompatActivity {

    private TextView groupTitle;
    private ImageButton showSchedule;
    private ImageButton showMember;
    private String groupName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent intent = getIntent();
        groupName = intent.getStringExtra("name");

        groupTitle = findViewById(R.id.tv_group_name);
        showSchedule = findViewById(R.id.button_group_schedule);
        showMember = findViewById(R.id.button_group_member);

        showSchedule.setOnClickListener(v -> {
            Intent scheduleIntent = new Intent(GroupSingleActivity.this, GroupScheduleActivity.class);
            startActivity(scheduleIntent);
        });

        showMember.setOnClickListener(v -> {
            Intent memberIntent = new Intent(GroupSingleActivity.this, GroupMemberActivity.class);
            startActivity(memberIntent);
        });

        groupTitle.setText(groupName);
    }
}
