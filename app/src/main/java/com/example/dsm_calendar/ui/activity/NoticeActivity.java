package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.NoticeContract;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.NoticeRepository;
import com.example.dsm_calendar.presenter.NoticePresenter;
import com.example.dsm_calendar.ui.adapter.NoticeRVAdapter;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity implements NoticeContract.View {

    private ImageButton offButton;
    private RecyclerView noticeRecyclerView;
    private NoticeRVAdapter adapter;
    private ImageButton noticeAddButton;

    private NoticePresenter noticePresenter = new NoticePresenter(this, new NoticeRepository());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        offButton = findViewById(R.id.button_notice_off);
        noticeRecyclerView = findViewById(R.id.rv_notice);
        noticeAddButton = findViewById(R.id.button_notice_add);

        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeRVAdapter(this, noticePresenter);

        noticeRecyclerView.setAdapter(adapter);

        offButton.setOnClickListener( v -> finish());
        noticeAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MakeNoticeActivity.class);
            startActivity(intent);
        });

        noticePresenter.onStarted();
    }

    @Override
    public void addItems(ArrayList<Notice> noticeList) {
        adapter.noticeList = noticeList;
    }

    @Override
    public void startNoticeDetailActivity(String title, String content) {
        Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        startActivity(intent);
    }
}
