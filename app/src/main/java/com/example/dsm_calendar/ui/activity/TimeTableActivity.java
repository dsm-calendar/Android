package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.TimeTableContract;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.TimeTableRepository;
import com.example.dsm_calendar.presenter.TimeTablePresenter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Queue;

public class TimeTableActivity extends AppCompatActivity implements TimeTableContract.View {

    private ImageButton timeTableOff;
    private ImageButton timeTableEdit;
    private TextView tableTitle;
    private TableLayout table;
    private ArrayList<EditText> tables;
    private int curGrade = 1;
    private int curClass = 1;
    private boolean isEditMode = false;

    private TimeTablePresenter presenter = new TimeTablePresenter(this, new TimeTableRepository(this));
    private ArrayList<TimeTableUnit> timeTableUnits = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        timeTableOff = findViewById(R.id.button_timetable_off);
        timeTableEdit = findViewById(R.id.button_timetable_edit);
        tableTitle = findViewById(R.id.tv_timetable_tableTitle);
        tableTitle.setText(String.format("%d학년%d반 시간표", curGrade, curClass));

        initTableArray();

        timeTableOff.setOnClickListener(v -> finish());
        timeTableEdit.setOnClickListener(v -> {
            if (isEditMode){
                syncTimeTable();
                presenter.onEditSaveClicked(timeTableUnits);
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

    private void syncTimeTable() {
        ArrayList<TimeTableUnit> units = new ArrayList<>();

        for (int i = 0; i < tables.size(); ++i) {
            String rawInfo = tables.get(i).getText().toString();
            if (rawInfo.indexOf('\n') == -1) continue;

            int index = Integer.parseInt(String.format("%d%d%d%d", curGrade, curClass, i % 5, i / 5));
            String[] tableInfo = rawInfo.split("\n");
            units.add(new TimeTableUnit(tableInfo[0], tableInfo[1], index));
        }


        int index = getStartIndex();
        int page = curGrade * 10 + curClass;
        for (Iterator<TimeTableUnit> it = timeTableUnits.iterator(); it.hasNext();) {
            TimeTableUnit val = it.next();
            if ((val.getTimeTableIndex() / 100) == page)
                it.remove();
        }

        timeTableUnits.addAll(index, units);
    }

    private int getStartIndex() {
        int page = curGrade * 10 + curClass;
        for (int index = 0; index < this.timeTableUnits.size(); ++index) {
            if (this.timeTableUnits.get(index).getTimeTableIndex() / 100 == page)
                return index;
        }

        return 0;
    }

    private void setTableText(ArrayList<TimeTableUnit> timeTableUnits) {
        for (EditText table : tables)
            table.setText("");

        for (int i = 0; i < timeTableUnits.size(); ++i)
            tables.get(i).setText(timeTableUnits.get(i).getSubject()+"\n"+timeTableUnits.get(i).getTeacher());
    }

    public void setGrade(View v) {
        String gradeStr = ((Button)v).getText().toString();
        int nowGrade = gradeStr.charAt(0) - '0';
        setGrade(nowGrade);
        tableTitle.setText(String.format("%d학년%d반 시간표", curGrade, curClass));

        setTableText(getCurrentTable(curGrade, curClass));
    }

    public void setClass(View v) {
        String classStr = ((Button)v).getText().toString();
        int nowClass = classStr.charAt(0) - '0';
        setClass(nowClass);
        tableTitle.setText(String.format("%d학년%d반 시간표", curGrade, curClass));

        setTableText(getCurrentTable(curGrade, curClass));
    }

    private void setGrade(int inGrade) {
        curGrade = inGrade;
    }

    private void setClass(int inClass) {
        curClass = inClass;
    }

    private ArrayList<TimeTableUnit> getCurrentTable(int nowGrade, int nowClass){
        ArrayList<TimeTableUnit> timeTable = new ArrayList<>();

        for(TimeTableUnit unit : timeTableUnits){
            if (Integer.toString(unit.getTimeTableIndex()).startsWith(Integer.toString(nowGrade)+ nowClass)){
                timeTable.add(unit);
            }
        }
        return timeTable;
    }

    @Override
    public void getTimeTable(ArrayList<TimeTableUnit> tableUnits) {
        timeTableUnits = tableUnits;
        Collections.sort(timeTableUnits, (o1, o2) -> Integer.compare(o1.getTimeTableIndex(), o2.getTimeTableIndex()));
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
