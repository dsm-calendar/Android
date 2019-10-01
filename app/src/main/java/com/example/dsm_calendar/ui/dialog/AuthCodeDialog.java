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

    private TextInputLayout textInputLayout;
    private TextInputEditText editText;
    private ImageButton offButton;
    private ImageButton checkButton;
    private View.OnClickListener offButtonListener;
    private View.OnClickListener checkButtonListener;

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

        setContentView(R.layout.dialog_accesscode);

        textInputLayout = findViewById(R.id.til_accesscode_code);
        editText = findViewById(R.id.tiet_accesscode_code);
        offButton = findViewById(R.id.button_accesscode_off);
        checkButton = findViewById(R.id.button_accesscode_confirm);

        offButton.setOnClickListener(offButtonListener);
        checkButton.setOnClickListener(checkButtonListener);
    }
}
