package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class MakeNoticeActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton offButton;
    private Button cancelButton;
    private Button requestButton;
    private EditText titleEditText;
    private EditText contentsEditText;

    private String title;
    private String content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makenotice);

        offButton = findViewById(R.id.button_makeNotice_off);
        cancelButton = findViewById(R.id.button_makeNotice_buttons_cancel);
        requestButton = findViewById(R.id.button_makeNotice_buttons_makeNotice);
        titleEditText = findViewById(R.id.et_makeNotice_edits_title);
        contentsEditText = findViewById(R.id.et_makeNotice_edits_contents);

        offButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        requestButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_makeNotice_off:
            case R.id.button_makeNotice_buttons_cancel:
                finish();
                break;
            case R.id.button_makeNotice_buttons_makeNotice:
                title = titleEditText.getText().toString();
                content = contentsEditText.getText().toString();
                if (isAllFilled()){
                    //TODO: call presenter
                } else {
                    Toast.makeText(this, "비어있는 입력창이 있습니다.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean isAllFilled(){
        return title != null && content != null;
    }
}
