package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.data.SampleBanner;
import com.example.dsm_calendar.ui.adapter.BannerManageAdapter;

import java.util.ArrayList;

public class BannerManageActivity extends AppCompatActivity {

    private ImageButton offButton;
    private RecyclerView recyclerView;
    private BannerManageAdapter adapter;

    private ArrayList<SampleBanner> sample = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannermanage);

        offButton = findViewById(R.id.button_bannermanage_off);
        offButton.setOnClickListener(v -> finish());

        adapter = new BannerManageAdapter();

        recyclerView = findViewById(R.id.rv_bannermanage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        sample.add(new SampleBanner(R.drawable.sample_car, "asdjkasndasnkdjnakdnwunadjndwnadnadjk"));
        sample.add(new SampleBanner(R.drawable.sample_universe, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        sample.add(new SampleBanner(R.drawable.sample_ocean, "kokokokokokokokokokokokokokoko"));
        sample.add(new SampleBanner(R.drawable.sample_sportscar, "asdjkasndasnwifwenjvjenjdjk"));

        addItems(sample);
    }

    public void addItems(ArrayList<SampleBanner> bannerList){
        adapter.bannerList = bannerList;
    }
}
