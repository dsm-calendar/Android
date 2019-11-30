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
    private int groupId;
    private int groupScheduleId;
    private String groupName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent intent = getIntent();
        groupId = intent.getIntExtra("roomId", -1);
        groupScheduleId = intent.getIntExtra("groupScheduleId", -1);
        groupName = intent.getStringExtra("roomTitle");

        groupTitle = findViewById(R.id.tv_group_name);
        showSchedule = findViewById(R.id.button_group_schedule);
        showMember = findViewById(R.id.button_group_member);

        showSchedule.setOnClickListener(v -> {
            Intent scheduleIntent = new Intent(GroupSingleActivity.this, GroupScheduleActivity.class);
            scheduleIntent.putExtra("roomId", groupId);
            scheduleIntent.putExtra("groupScheduleId", groupScheduleId);
            startActivity(scheduleIntent);
        });

        showMember.setOnClickListener(v -> {
            Intent memberIntent = new Intent(GroupSingleActivity.this, GroupMemberActivity.class);
            memberIntent.putExtra("roomId", groupId);
            startActivity(memberIntent);
        });

        groupTitle.setText(groupName);
    }
}
