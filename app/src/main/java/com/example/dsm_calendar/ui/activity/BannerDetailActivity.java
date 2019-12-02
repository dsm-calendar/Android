package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.BannerDetailContract;
import com.example.dsm_calendar.data.BannerDetailRepository;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.presenter.BannerDetailPresenter;
import com.squareup.picasso.Picasso;

public class BannerDetailActivity extends AppCompatActivity implements BannerDetailContract.View {

    private ImageView image;
    private TextView summary;
    private ImageButton offButton;
    private ImageButton manageButton;
    int eventId;

    private BannerDetailPresenter presenter = new BannerDetailPresenter(this, new BannerDetailRepository(this));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannerdetail);

        image = findViewById(R.id.iv_bannerDetail_image);
        summary = findViewById(R.id.tv_bannerDetail_summary);
        offButton = findViewById(R.id.button_bannerDetail_off);
        manageButton = findViewById(R.id.button_bannerDetail_manage);

        Intent intent = getIntent();
        eventId = intent.getIntExtra("eventId", -1);

        offButton.setOnClickListener(v -> finish());
        manageButton.setOnClickListener(v -> {
            Intent managePageIntent = new Intent(BannerDetailActivity.this, BannerManageActivity.class);
            startActivity(managePageIntent);
        });
        presenter.onStarted(eventId);
    }

    @Override
    public void loadEvent(Event event) {
        Picasso.with(this).load(event.getEventPoster()).into(image);
        summary.setText(event.getEventDetail());
    }

    @Override
    public void showMessageForLoadingFail(String message) {
        Toast.makeText(this, "event loading fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }
}
