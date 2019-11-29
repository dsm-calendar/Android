package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.NoticeContract;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.NoticeRepository;
import com.example.dsm_calendar.data.Singleton.UserPreference;
import com.example.dsm_calendar.presenter.NoticePresenter;
import com.example.dsm_calendar.ui.adapter.NoticeRVAdapter;

import java.util.ArrayList;

public class NoticeActivity extends AppCompatActivity implements NoticeContract.View {

    private ImageButton offButton;
    private RecyclerView noticeRecyclerView;
    private NoticeRVAdapter adapter;
    private ImageButton noticeAddButton;
    private boolean isAdmin = UserPreference.getInstance(this).getIsAdmin();
    //TODO have to test about this code

    private NoticePresenter noticePresenter = new NoticePresenter(this, new NoticeRepository(this));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        offButton = findViewById(R.id.button_notice_off);
        noticeRecyclerView = findViewById(R.id.rv_notice);
        noticeAddButton = findViewById(R.id.button_notice_add);

        adminMode(UserPreference.getInstance(this).getIsAdmin());

        noticeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeRVAdapter(this, noticePresenter, UserPreference.getInstance(this).getIsAdmin());

        noticeRecyclerView.setAdapter(adapter);

        offButton.setOnClickListener( v -> finish());
        noticeAddButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MakeNoticeActivity.class);
            startActivity(intent);
        });

        noticePresenter.onStarted();
    }

    @Override
    public void showMessageForDeleteNoticeSuccess() {
        Toast.makeText(this, "Notice Deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForDeleteNoticeFail(String message) {
        Toast.makeText(this, "Delete Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForLoadingFail(String message) {
        Toast.makeText(this, "Loading Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void addItems(ArrayList<Notice> noticeList) {
        adapter.noticeList = noticeList;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void startNoticeDetailActivity(String title, String content) {
        Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        startActivity(intent);
    }

    private void adminMode(boolean isAdmin){
        if (isAdmin){
            noticeAddButton.setVisibility(View.VISIBLE);
        } else {
            noticeAddButton.setVisibility(View.GONE);
        }
    }
}
