package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.dialog.SelectDateDialog;
import com.example.dsm_calendar.util.DialogListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;

public class AddScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton addScheduleOff;
    private EditText title;
    private EditText content;
    private ConstraintLayout startDay;
    private ConstraintLayout endDay;
    private Button cancel;
    private Button confirm;

    private CalendarDay startDate;
    private CalendarDay endDate;
    private String scheduleTitle;
    private String scheduleContent;

    private SelectDateDialog selectDateDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);

        addScheduleOff = findViewById(R.id.button_addschedule_off);
        title = findViewById(R.id.et_addschedule_title);
        content = findViewById(R.id.et_addschedule_content);
        startDay = findViewById(R.id.cl_addschedule_startday);
        endDay = findViewById(R.id.cl_addschedule_endday);
        cancel = findViewById(R.id.button_addschedule_cancel);
        confirm = findViewById(R.id.button_addschedule_confirm);

        selectDateDialog = new SelectDateDialog(this);

        addScheduleOff.setOnClickListener(v -> finish());
        startDay.setOnClickListener(this);
        endDay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cl_addschedule_startday:
                selectDateDialog.setSelectDateDialogListener(date -> {
                    //TODO: get selected start date here
                    startDate = date;
                    Toast.makeText(AddScheduleActivity.this, "start day", Toast.LENGTH_LONG).show();
                });
                selectDateDialog.setDialogTitle("시작일");
                selectDateDialog.show();
                break;
            case R.id.cl_addschedule_endday:
                selectDateDialog.setSelectDateDialogListener(date -> {
                    //TODO: get selected end date here
                    endDate = date;
                    Toast.makeText(AddScheduleActivity.this, "end day", Toast.LENGTH_LONG).show();
                });
                selectDateDialog.setDialogTitle("종료일");
                selectDateDialog.show();
                break;
            case R.id.button_addschedule_cancel:
            case R.id.button_addschedule_off:
                finish();
                break;
            case R.id.button_addschedule_confirm:
                scheduleTitle = title.getText().toString();
                scheduleContent = content.getText().toString();
                finish();
                break;
        }
    }
}
