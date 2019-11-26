package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.BannerManageContract;
import com.example.dsm_calendar.data.BannerManageRepository;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.SampleBanner;
import com.example.dsm_calendar.presenter.BannerManagePresenter;
import com.example.dsm_calendar.ui.adapter.BannerManageAdapter;

import java.util.ArrayList;

public class BannerManageActivity extends AppCompatActivity implements BannerManageContract.View {

    private ImageButton offButton;
    private RecyclerView recyclerView;
    private BannerManageAdapter adapter;

    private BannerManagePresenter presenter = new BannerManagePresenter(this, new BannerManageRepository(this));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannermanage);

        offButton = findViewById(R.id.button_bannermanage_off);
        offButton.setOnClickListener(v -> finish());

        adapter = new BannerManageAdapter(this, presenter);

        recyclerView = findViewById(R.id.rv_bannermanage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        presenter.onStarted();
    }

    @Override
    public void addBannerList(ArrayList<Event> banners) {
        adapter.bannerList = banners;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void manageBanner(int position, boolean eventStatus) {
        adapter.bannerList.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMessageForBannerManageSuccess(boolean eventStatus) {
        String manageStatus;
        if (eventStatus){
            manageStatus = "accept";
        } else {
            manageStatus = "reject";
        }
        Toast.makeText(this, String.format("Banner %s Success", manageStatus), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForBannerManageFail(boolean eventStatus, String message) {
        String manageStatus;
        if (eventStatus){
            manageStatus = "accept";
        } else {
            manageStatus = "reject";
        }
        Toast.makeText(this,  String.format("Banner %s Fail\nmessage: %s", manageStatus, message), Toast.LENGTH_LONG).show();
    }
}
