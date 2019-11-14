package com.example.dsm_calendar.ui.activity;

import android.content.Intent;
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
import com.example.dsm_calendar.contract.AddScheduleContract;
import com.example.dsm_calendar.data.AddScheduleRepository;
import com.example.dsm_calendar.data.Singleton.BusProvider;
import com.example.dsm_calendar.presenter.AddSchedulePresenter;
import com.example.dsm_calendar.ui.dialog.SelectDateDialog;
import com.example.dsm_calendar.util.ScheduleEvent;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddScheduleActivity extends AppCompatActivity implements AddScheduleContract.View, View.OnClickListener {

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

    private AddSchedulePresenter presenter = new AddSchedulePresenter(this, new AddScheduleRepository());

    private SelectDateDialog selectDateDialog;

    private String scheduleCode;

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

        today.setText(String.format("오늘: %s", dateFormat.format(date)));

        addScheduleOff.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        startDay.setOnClickListener(this);
        endDay.setOnClickListener(this);

        Intent intent = getIntent();
        scheduleCode = intent.getStringExtra("schedule code");
    }

    @Override
    public void showMessageForSuccess() {
        Toast.makeText(this, "success!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageForFail(String message) {
        Toast.makeText(this, "fail!\n" + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishActivity() {
        BusProvider.getInstance().post(new ScheduleEvent(ScheduleEvent.EVENT.SCHEDULE_ADD));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cl_addschedule_startday:
                selectDateDialog.setSelectDateDialogListener(date -> {
                    startDate = date;
                    startDateText.setText(dateFormat.format(startDate.getDate()));
                });
                selectDateDialog.setDialogTitle("시작일");
                selectDateDialog.show();
                break;
            case R.id.cl_addschedule_endday:
                selectDateDialog.setSelectDateDialogListener(date -> {
                    endDate = date;
                    endDateText.setText(dateFormat.format(endDate.getDate()));
                });
                selectDateDialog.setDialogTitle("종료일");
                selectDateDialog.show();
                break;
            case R.id.button_addschedule_cancel:
            case R.id.button_addschedule_off:
                BusProvider.getInstance().post(new ScheduleEvent(ScheduleEvent.EVENT.JUST_FINISHED));
                finish();
                break;
            case R.id.button_addschedule_confirm:
                scheduleTitle = title.getText().toString();
                scheduleContent = content.getText().toString();
                if (isAllChecked()) {
                    presenter.onSaveClicked(scheduleCode, scheduleTitle, scheduleContent, startDate.getDate().toString(), endDate.getDate().toString());
                } else {
                    Toast.makeText(this, "모든 칸이 채워지지 않았습니다.", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private boolean isAllChecked() {
        return !scheduleTitle.equals("") && !scheduleContent.equals("") && startDate != null && endDate != null;
    }
}
