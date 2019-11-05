package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class BannerDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private TextView summary;
    private ImageButton offButton;
    private ImageButton editButton;
    int bannerImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannerdetail);

        image = findViewById(R.id.iv_bannerDetail_image);
        summary = findViewById(R.id.tv_bannerDetail_summary);
        offButton = findViewById(R.id.button_bannerDetail_off);
        editButton = findViewById(R.id.button_bannerDetail_change);

        Intent intent = getIntent();
        bannerImage = intent.getIntExtra("image", 0);
        if (bannerImage == 0){
            Toast.makeText(this, "image error", Toast.LENGTH_SHORT).show();
        } else {
            image.setImageResource(bannerImage);
        }

        offButton.setOnClickListener(this);
        editButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_bannerDetail_off:
                finish();
                break;
            case R.id.button_bannerDetail_change:
                //TODO: set visible, invisible due to user's authorization
                Intent intent = new Intent(BannerDetailActivity.this, BannerManageActivity.class);
                startActivity(intent);
                break;
        }
    }

}
