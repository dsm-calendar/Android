package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.adapter.MessageRVAdapter;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MessageRVAdapter adapter;
    ArrayList<String> messageList = new ArrayList<>();
    ArrayList<String> dateList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.message_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_cross_out);

        messageList.add("동휘님이 게임만들기 프로젝트에 당신을 초대하셨습니다");
        messageList.add("윤성님이 야구그룹에 당신을 초대하셨습니다");
        messageList.add("승민님이 헬스장에 당신을 초대하셨습니다");
        messageList.add("하경님이 이상한 곳에 당신을 초대하셨습니다");
        messageList.add("누군가가 대마고에 당신을 초대하셨습니다");
        messageList.add("경고: 당신은 사람입니다");
        messageList.add("안녕하세요 dsm-calendar에 오신 것을 환영합니다");
        dateList.add("2019.01.03");
        dateList.add("2019.02.14");
        dateList.add("2019.02.22");
        dateList.add("2019.05.05");
        dateList.add("2019.05.10");
        dateList.add("2019.06.29");
        dateList.add("2019.07.02");

        recyclerView = findViewById(R.id.message_rv);
        adapter = new MessageRVAdapter(messageList, dateList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}