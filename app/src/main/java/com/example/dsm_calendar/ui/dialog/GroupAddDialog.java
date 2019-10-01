package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;

public class GroupAddDialog extends Dialog {

    private View.OnClickListener offButtonListener;
    private View.OnClickListener checkButtonListener;
    private EditText editText;
    private ImageView offButton;
    private ImageButton checkButton;

    public GroupAddDialog(
            @NonNull Context context,
            View.OnClickListener offButtonListener,
            View.OnClickListener checkButtonListener) {
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

        setContentView(R.layout.dialog_addgroup);

        editText = findViewById(R.id.et_addgroup_name);
        offButton = findViewById(R.id.button_addgroup_off);
        checkButton = findViewById(R.id.button_addgroup_confirm);

        offButton.setOnClickListener(offButtonListener);
        checkButton.setOnClickListener(checkButtonListener);
    }
}
