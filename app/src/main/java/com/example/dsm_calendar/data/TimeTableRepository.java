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

        listener.onSuccess(timeTableUnits);
    }

    @Override
    public void editSave(EditSaveListener listener) {
        listener.onSuccess();
    }
}
