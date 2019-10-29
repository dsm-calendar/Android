package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.ui.adapter.NoticeRVAdapter;

public class NoticeActivity extends AppCompatActivity {

    private ImageButton offButton;
    private RecyclerView noticeRecyclerView;
    private NoticeRVAdapter adapter;
    private ImageButton noticeAddButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        offButton = findViewById(R.id.button_notice_off);
        noticeRecyclerView = findViewById(R.id.rv_notice);
        noticeAddButton = findViewById(R.id.button_notice_add);

        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeRVAdapter(this);
        adapter.noticeList.add(new Notice("sample", "aldajksdnadkjanjkdniwnadjkw"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        adapter.noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));

        noticeRecyclerView.setAdapter(adapter);

        offButton.setOnClickListener( v -> finish());
        noticeAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MakeNoticeActivity.class);
            startActivity(intent);
        });
    }
}
