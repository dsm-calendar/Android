package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.SignUpContract;
import com.example.dsm_calendar.data.SignUpRepository;
import com.example.dsm_calendar.presenter.SignUpPresenter;

public class SignUpActivity extends AppCompatActivity implements SignUpContract.View {

    private EditText id;
    private EditText password;
    private EditText std_no;
    private Button confirm;

    private SignUpPresenter presenter = new SignUpPresenter(this, new SignUpRepository());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        id = findViewById(R.id.et_sign_in_id);
        password = findViewById(R.id.et_sign_in_password);
        std_no = findViewById(R.id.et_sign_in_std_no);
        confirm = findViewById(R.id.button_sign_in_confirm);

        confirm.setOnClickListener(v -> presenter.onClickSignUp());
    }

    @Override
    public String getID() {
        return id.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public int getStd_no() {
        return Integer.parseInt(std_no.getText().toString());
    }

    @Override
    public void showMessageForSuccess() {
        Toast.makeText(this, "success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForFail(String message) {
        Toast.makeText(this, "message: "+message, Toast.LENGTH_LONG).show();
    }
}
