package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.LoginContract;
import com.example.dsm_calendar.data.LoginRepository;
import com.example.dsm_calendar.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private Button joinButton;
    private EditText id;
    private EditText password;
    private ImageButton confirm;

    private LoginPresenter presenter = new LoginPresenter(this, new LoginRepository(this));

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        joinButton = findViewById(R.id.button_login_join);
        id = findViewById(R.id.et_login_id);
        password = findViewById(R.id.et_login_pw);
        confirm = findViewById(R.id.button_login_confirm);

        joinButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        confirm.setOnClickListener(v -> presenter.onClickConfirm());
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
    public void startMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessageForSuccess() {
        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForFail(String message) {
        Toast.makeText(this, "message: "+message, Toast.LENGTH_LONG).show();
    }
}
