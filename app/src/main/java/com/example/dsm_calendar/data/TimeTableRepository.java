package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.TimeTableContract;

public class TimeTableRepository implements TimeTableContract.Repository {

    public interface GetTimeTableListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface EditSaveListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getTimeTable(GetTimeTableListener listener) {
        listener.onSuccess();
    }

    @Override
    public void editSave(EditSaveListener listener) {
        listener.onSuccess();
    }
}
