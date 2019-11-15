package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.TimeTableContract;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;

import java.util.ArrayList;

public class TimeTableRepository implements TimeTableContract.Repository {

    public interface GetTimeTableListener{
        void onSuccess(ArrayList<TimeTableUnit> tableUnits);
        void onFail(String message);
    }

    public interface EditSaveListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getTimeTable(GetTimeTableListener listener) {
        ArrayList<TimeTableUnit> timeTableUnits = new ArrayList<>();

        timeTableUnits.add(new TimeTableUnit("체육\n장필준", 1111));
        timeTableUnits.add(new TimeTableUnit("문학\n장보현", 1211));
        timeTableUnits.add(new TimeTableUnit("미술\n강래형", 1311));
        timeTableUnits.add(new TimeTableUnit("영어\n임채홍", 1411));
        timeTableUnits.add(new TimeTableUnit("네트\n이경희", 2111));
        timeTableUnits.add(new TimeTableUnit("DB\n안현수", 2211));
        timeTableUnits.add(new TimeTableUnit("자바\n신요셉", 2311));
        timeTableUnits.add(new TimeTableUnit("수학\n설은선", 2411));

        listener.onSuccess(timeTableUnits);
    }

    @Override
    public void editSave(EditSaveListener listener) {
        listener.onSuccess();
    }
}
