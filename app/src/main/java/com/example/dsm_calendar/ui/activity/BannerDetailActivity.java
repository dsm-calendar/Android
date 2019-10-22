package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class BannerDetailActivity extends AppCompatActivity {

    private ImageView image;
    private TextView summary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannerdetail);

        image = findViewById(R.id.iv_bannerDetail_image);
        summary = findViewById(R.id.tv_bannerDetail_summary);
    }
}
