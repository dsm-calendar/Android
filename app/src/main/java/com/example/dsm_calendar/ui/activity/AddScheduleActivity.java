package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.ui.dialog.SelectDateDialog;
import com.example.dsm_calendar.util.DialogListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddScheduleActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton addScheduleOff;
    private TextView today;
    private EditText title;
    private EditText content;
    private ConstraintLayout startDay;
    private ConstraintLayout endDay;
    private TextView startDateText;
    private TextView endDateText;
    private Button cancel;
    private Button confirm;

    private CalendarDay startDate;
    private CalendarDay endDate;
    private String scheduleTitle;
    private String scheduleContent;
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);

    private SelectDateDialog selectDateDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addschedule);

        addScheduleOff = findViewById(R.id.button_addschedule_off);
        today = findViewById(R.id.tv_addschedule_today);
        title = findViewById(R.id.et_addschedule_title);
        content = findViewById(R.id.et_addschedule_content);
        startDay = findViewById(R.id.cl_addschedule_startday);
        endDay = findViewById(R.id.cl_addschedule_endday);
        startDateText = findViewById(R.id.tv_addschedule_startday);
        endDateText = findViewById(R.id.tv_addschedule_endday);
        cancel = findViewById(R.id.button_addschedule_cancel);
        confirm = findViewById(R.id.button_addschedule_confirm);

        selectDateDialog = new SelectDateDialog(this);

        today.setText(dateFormat.format(date));

        addScheduleOff.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        startDay.setOnClickListener(this);
        endDay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_addschedule_startday:
                selectDateDialog.setSelectDateDialogListener(date -> {
                    startDate = date;
                    startDateText.setText(dateFormat.format(startDate.getDate()));
                    Toast.makeText(AddScheduleActivity.this, "start day", Toast.LENGTH_LONG).show();
                });
                selectDateDialog.setDialogTitle("시작일");
                selectDateDialog.show();
                break;
            case R.id.cl_addschedule_endday:
                selectDateDialog.setSelectDateDialogListener(date -> {
                    endDate = date;
                    endDateText.setText(dateFormat.format(endDate.getDate()));
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
                //not return null when not filled
                scheduleContent = content.getText().toString();
                //not return null when not filled
                if (isAllChecked()) {
                    finish();
                } else {
                    Toast.makeText(this, "모든 칸이 채워지지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean isAllChecked() {
        return scheduleTitle != null && scheduleContent != null && startDate != null && endDate != null;
    }
}
