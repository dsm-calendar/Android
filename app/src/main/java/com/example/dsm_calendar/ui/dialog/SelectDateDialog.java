package com.example.dsm_calendar.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.util.DialogListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Date;

public class SelectDateDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private ImageButton offButton;
    private TextView titleTextView;
    private MaterialCalendarView calendarView;
    private ImageButton confirm;

    private CalendarDay selectedDate;
    private Date date = new Date();

    private DialogListener.SelectDateDialogListener listener;
    private boolean created = false;

    private String title;

    public SelectDateDialog(@NonNull Context context) {
        super(context);
        this.context = context;
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

        calendarView.setOnDateChangedListener((widget, date, selected) -> selectedDate = date);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        created = !created;
        titleTextView.setText(title);
        calendarView.setSelectedDate(date);
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
                if (selectedDate == null){
                    Toast.makeText(context, "선택된 날짜가 없습니다.", Toast.LENGTH_LONG).show();
                } else {
                    listener.onClickConfirm(selectedDate);
                }
                dismiss();
                break;
        }
    }
}
