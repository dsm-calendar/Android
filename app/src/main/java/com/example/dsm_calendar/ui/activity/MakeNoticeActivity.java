package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class MakeNoticeActivity extends AppCompatActivity implements View.OnClickListener{

    Button cancelButton;
    Button requestButton;
    EditText titleEditText;
    EditText contentsEditText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makenotice);

        cancelButton = findViewById(R.id.button_makeNotice_buttons_cancel);
        requestButton = findViewById(R.id.button_makeNotice_buttons_makeNotice);
        titleEditText = findViewById(R.id.et_makeNotice_edits_title);
        contentsEditText = findViewById(R.id.et_makeNotice_edits_contents);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_makeNotice_off:
            case R.id.button_makeNotice_buttons_cancel:
                finish();
                break;
            case R.id.button_makeNotice_buttons_makeNotice:
                //TODO: call presenter
        }
    }
}
