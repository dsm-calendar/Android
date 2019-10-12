package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;

public class SignInActivity extends AppCompatActivity {

    private EditText id;
    private EditText password;
    private EditText std_no;
    private Button confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        id = findViewById(R.id.et_sign_in_id);
        password = findViewById(R.id.et_sign_in_password);
        std_no = findViewById(R.id.et_sign_in_std_no);
        confirm = findViewById(R.id.button_sign_in_confirm);
    }
}
