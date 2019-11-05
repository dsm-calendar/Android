package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class BannerRequireActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton offButton;
    private ImageView selectedImage;
    private Button selectImageButton;
    private TextView selectedTextView;
    private EditText bannerContentes;
    private Button bannerRequireCancel;
    private Button bannerRequire;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bannerrequire);

        offButton = findViewById(R.id.button_bannerrequire_off);
        selectedImage = findViewById(R.id.iv_bannerrequire_selected);
        selectImageButton = findViewById(R.id.button_bannerrequire_addimage);
        selectedTextView = findViewById(R.id.tv_bannerrequire_selected);
        bannerContentes = findViewById(R.id.et_bannerrequire_edits_contents);
        bannerRequireCancel = findViewById(R.id.button_bannerrequire_buttons_cancel);
        bannerRequire = findViewById(R.id.button_bannerrequire_buttons_requirebanner);

        offButton.setOnClickListener(this);
        selectImageButton.setOnClickListener(this);
        selectedTextView.setOnClickListener(this);
        bannerRequireCancel.setOnClickListener(this);
        bannerRequire.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_bannerrequire_off:
            case R.id.button_bannerrequire_buttons_cancel:
                finish();
                break;
            case R.id.button_bannerrequire_addimage:
            case R.id.tv_bannerrequire_selected:
                //select image from gallery
                break;
            case R.id.button_bannerrequire_buttons_requirebanner:
                selectedImage.getDrawable();
                bannerContentes.getText().toString();
                break;
        }
    }
}
