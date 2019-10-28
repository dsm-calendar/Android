package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class SelectDateDialog extends Dialog implements View.OnClickListener {

    private ImageButton offButton;
    private TextView titleTextView;
    private MaterialCalendarView calendarView;
    private ImageButton confirm;

    private DialogListener.SelectDateDialogListener listener;
    private boolean created = false;

    private String title;

    public SelectDateDialog(@NonNull Context context) {
        super(context);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;

        getWindow().setAttributes(layoutParams);

        setContentView(R.layout.dialog_selectdate);
        offButton = findViewById(R.id.button_selectdate_off);
        titleTextView = findViewById(R.id.tv_selectdate_title);
        calendarView = findViewById(R.id.selectdate_calendar);
        confirm = findViewById(R.id.button_selectdate_confirm);

        offButton.setOnClickListener(this);
        confirm.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        created = !created;
        titleTextView.setText(title);
    }

    public void setSelectDateDialogListener(DialogListener.SelectDateDialogListener listener){
        this.listener = listener;
    }

    public void setDialogTitle(String title){
        this.title = title;
        if(created){
            titleTextView.setText(title);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_selectdate_off:
                dismiss();
                break;
            case R.id.button_selectdate_confirm:
                listener.onClickConfirm();
                dismiss();
                break;
        }
    }
}
