package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;

public class ScheduleAddDialog extends Dialog {

    EditText title;
    EditText content;
    ImageButton offButton;
    ImageButton checkButton;
    View.OnClickListener offButtonListener;
    View.OnClickListener checkButtonListener;

    public ScheduleAddDialog(@NonNull Context context, View.OnClickListener offButtonListener, View.OnClickListener checkButtonListener) {
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

        setContentView(R.layout.dialog_add_schedule);

        title = findViewById(R.id.et_schedule_add_title);
        content = findViewById(R.id.et_schedule_add_content);
        offButton = findViewById(R.id.button_dialog_schedule_add_off);
        checkButton = findViewById(R.id.button_schedule_add);

        offButton.setOnClickListener(offButtonListener);
        checkButton.setOnClickListener(checkButtonListener) ;
    }
}
