package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AuthCodeDialog extends Dialog {

    TextInputLayout textInputLayout;
    TextInputEditText editText;
    ImageButton offButton;
    ImageButton checkButton;
    View.OnClickListener offButtonListener;
    View.OnClickListener checkButtonListener;

    public AuthCodeDialog(@NonNull Context context, View.OnClickListener offButtonListener, View.OnClickListener checkButtonListener) {
        super(context);
        this.offButtonListener = offButtonListener;
        this.checkButtonListener = checkButtonListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_authorization_code);

        textInputLayout = findViewById(R.id.tl_dialog_auth);
        editText = findViewById(R.id.et_dialog_auth);
        offButton = findViewById(R.id.button_dialog_auth_off);
        checkButton = findViewById(R.id.button_dialog_auth);

        offButton.setOnClickListener(offButtonListener);
        checkButton.setOnClickListener(checkButtonListener);
    }
}
