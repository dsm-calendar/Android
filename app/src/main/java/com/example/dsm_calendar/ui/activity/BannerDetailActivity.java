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

public class BannerDetailActivity extends AppCompatActivity {

    private ImageView image;
    private TextView summary;
    private ImageButton offButton;
    private ImageButton manageButton;
    byte[] bannerImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannerdetail);

        image = findViewById(R.id.iv_bannerDetail_image);
        summary = findViewById(R.id.tv_bannerDetail_summary);
        offButton = findViewById(R.id.button_bannerDetail_off);
        manageButton = findViewById(R.id.button_bannerDetail_manage);

        Intent intent = getIntent();
        bannerImage = intent.getByteArrayExtra("image");
        if (bannerImage == null){
            Toast.makeText(this, "image error", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap bmp = BitmapFactory.decodeByteArray(bannerImage, 0, bannerImage.length);
            image.setImageBitmap(bmp);
        }

        offButton.setOnClickListener(v -> finish());
        manageButton.setOnClickListener(v -> {
            Intent managePageIntent = new Intent(BannerDetailActivity.this, BannerManageActivity.class);
            startActivity(managePageIntent);
        });
    }
}
