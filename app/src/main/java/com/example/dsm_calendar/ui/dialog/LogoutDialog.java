package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;

public class LogoutDialog extends Dialog implements View.OnClickListener {

    private Button confirm;
    private Button cancel;

    private DialogListener.LogoutDialogListener listener;

    public LogoutDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_logout);

        confirm = findViewById(R.id.button_logout_confirm);
        cancel = findViewById(R.id.button_logout_cancel);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_logout_confirm:
                listener.onConfirmClicked();
                dismiss();
                break;
            case R.id.button_logout_cancel:
                dismiss();
                break;
        }
    }

    public void setLogoutDialogListener(DialogListener.LogoutDialogListener listener){
        this.listener = listener;
    }
}
