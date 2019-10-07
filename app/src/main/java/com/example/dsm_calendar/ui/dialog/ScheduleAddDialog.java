package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;

public class ScheduleAddDialog extends Dialog implements View.OnClickListener{

    private EditText title;
    private EditText content;
    private TextView date;
    private ImageButton offButton;
    private ImageButton checkButton;
    private DialogListener.ScheduleAddDialogListener listener;

    private String selectedDate;

    public ScheduleAddDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_addschedule);

        title = findViewById(R.id.et_addschedule_title);
        content = findViewById(R.id.et_addschedule_content);
        date = findViewById(R.id.tv_addschedule_date);
        offButton = findViewById(R.id.button_addschedule_off);
        checkButton = findViewById(R.id.button_addschedule_confirm);

        date.setText(selectedDate);
        offButton.setOnClickListener(this);
        checkButton.setOnClickListener(this) ;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addschedule_off:
                dismiss();
                break;
            case R.id.button_addschedule_confirm:
                listener.onClickConfirm(title.getText().toString(), content.getText().toString());
                title.setText("");
                content.setText("");
                dismiss();
                break;
        }
    }

    public void setScheduleAddDialogListener(DialogListener.ScheduleAddDialogListener listener){
        this.listener = listener;
    }

    public void setDate(String date){
        this.selectedDate = date;
    }
}
