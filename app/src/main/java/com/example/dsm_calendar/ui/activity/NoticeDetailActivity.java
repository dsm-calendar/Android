package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class NoticeDetailActivity extends AppCompatActivity {

    private ImageButton offButton;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticedetail);

        offButton = findViewById(R.id.button_noticedetail_off);
        title = findViewById(R.id.tv_noticedetail_title);
        content = findViewById(R.id.tv_noticedetail_content);
    }
}
