package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
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

    private String stringTitle;
    private String stringContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticedetail);

        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("content")){
            stringTitle = intent.getStringExtra("title");
            stringContent = intent.getStringExtra("content");
        } else {
            stringTitle = "정보를 불러오는데 실패했습니다";
            stringContent = "정보를 불러오는데 실패했습니다";
        }

        offButton = findViewById(R.id.button_noticedetail_off);
        title = findViewById(R.id.tv_noticedetail_title);
        content = findViewById(R.id.tv_noticedetail_content);

        offButton.setOnClickListener(v -> finish());
        title.setText(stringTitle);
        content.setText(stringContent);
    }
}
