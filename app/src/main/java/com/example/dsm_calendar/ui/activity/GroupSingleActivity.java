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
    private ImageView groupImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        groupTitle = findViewById(R.id.tv_group_name);
        showSchedule = findViewById(R.id.button_group_schedule);
        showMember = findViewById(R.id.button_group_member);
        groupImage = findViewById(R.id.iv_group_image);

        showSchedule.setOnClickListener(v -> {
            Intent intent = new Intent(GroupSingleActivity.this, GroupScheduleActivity.class);
            startActivity(intent);
        });

        showMember.setOnClickListener(v -> {
            Intent intent = new Intent(GroupSingleActivity.this, GroupMemberActivity.class);
            startActivity(intent);
        });
    }
}
