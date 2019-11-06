package com.example.dsm_calendar.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.dsm_calendar.R;

import java.util.ArrayList;

public class TimeTableActivity extends AppCompatActivity {

    private ImageButton timeTableOff;
    private ImageButton timeTableEdit;
    private ArrayList<EditText> tables;
    private int curGrade = 1;
    private int curClass = 1;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        timeTableOff = findViewById(R.id.button_timetable_off);
        timeTableEdit = findViewById(R.id.button_timetable_edit);

        initTableArray();

        timeTableOff.setOnClickListener(v -> finish());
        timeTableEdit.setOnClickListener(v -> {
            isEditMode = !isEditMode;
            timeTableEdit.setImageDrawable(ContextCompat.getDrawable(this,
                    isEditMode ? R.drawable.ic_check_gray : R.drawable.ic_pencil_white));

            for (EditText table : tables)
                table.setEnabled(isEditMode);
        });
    }

    private void initTableArray() {
        TableLayout table = findViewById(R.id.tl_timetable_table);
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

        // TODO setTableText
    }

    public void setClass(View v) {
        String classStr = ((Button)v).getText().toString();
        int nowClass = classStr.charAt(0) - '0';
        setClass(nowClass);

        // TODO setTableText
    }

    private void setGrade(int inGrade) {
        curGrade = inGrade;
    }

    private void setClass(int inClass) {
        curClass = inClass;
    }
}
