package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.TimeTableContract;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.TimeTableRepository;
import com.example.dsm_calendar.presenter.TimeTablePresenter;

import java.util.ArrayList;

public class TimeTableActivity extends AppCompatActivity implements TimeTableContract.View {

    private ImageButton timeTableOff;
    private ImageButton timeTableEdit;
    private TableLayout table;
    private ArrayList<EditText> tables;
    private int curGrade = 1;
    private int curClass = 1;
    private boolean isEditMode = false;

    private TimeTablePresenter presenter = new TimeTablePresenter(this, new TimeTableRepository());
    private ArrayList<TimeTableUnit> timeTableUnits = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        timeTableOff = findViewById(R.id.button_timetable_off);
        timeTableEdit = findViewById(R.id.button_timetable_edit);

        initTableArray();

        timeTableOff.setOnClickListener(v -> finish());
        timeTableEdit.setOnClickListener(v -> {
            if (isEditMode){
                presenter.onEditSaveClicked();
            }
            isEditMode = !isEditMode;
            timeTableEdit.setImageDrawable(ContextCompat.getDrawable(this,
                    isEditMode ? R.drawable.ic_check_gray : R.drawable.ic_pencil_white));

            for (EditText table : tables)
                table.setEnabled(isEditMode);
        });

        presenter.onStarted();
    }

    private void initTableArray() {
        table = findViewById(R.id.tl_timetable_table);
        tables = new ArrayList<>();

        for(int i = 0; i < table.getChildCount(); ++i){
            TableRow row = (TableRow)table.getChildAt(i);

            for (int j = 0; j < row.getChildCount(); ++j) {
                View view = row.getChildAt(j);
                if (view instanceof EditText)
                    tables.add((EditText)view);
            }
        }
    }

    private void setTableText(ArrayList<String> texts) {
        for (int i = 0; i < tables.size(); ++i)
            tables.get(i).setText(texts.get(i));
    }

    public void setGrade(View v) {
        String gradeStr = ((Button)v).getText().toString();
        int nowGrade = gradeStr.charAt(0) - '0';
        setGrade(nowGrade);

        setTableText(getCurrentTable(curGrade, curClass));
    }

    public void setClass(View v) {
        String classStr = ((Button)v).getText().toString();
        int nowClass = classStr.charAt(0) - '0';
        setClass(nowClass);

        setTableText(getCurrentTable(curGrade, curClass));
    }

    private void setGrade(int inGrade) {
        curGrade = inGrade;
    }

    private void setClass(int inClass) {
        curClass = inClass;
    }

    private ArrayList<String> getCurrentTable(int nowGrade, int nowClass){
        ArrayList<String> timeTable = new ArrayList<>();
        for(TimeTableUnit unit : timeTableUnits){
            //TODO error in here
            if (Integer.toString(unit.getIndex()).startsWith(Integer.toString(nowGrade)+ nowClass)){
                timeTable.add(unit.getSubject());
            }
        }
        return timeTable;
    }

    @Override
    public void getTimeTable(ArrayList<TimeTableUnit> tableUnits) {
        this.timeTableUnits = tableUnits;
        setTableText(getCurrentTable(curGrade, curClass));
    }

    @Override
    public void showMessageForLoadFail(String message) {
        Toast.makeText(this, "Loading Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForEditSaveSuccess() {
        Toast.makeText(this, "Saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageForEditSaveFail(String message) {
        Toast.makeText(this, "Saving Fail\nmessage: " + message, Toast.LENGTH_LONG).show();
    }
}
