package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.AddScheduleContract;

public class AddScheduleRepository implements AddScheduleContract.Repository {

    public interface AddScheduleListener {
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void addSchedule(String title, String content, String startDay, String endDay, AddScheduleListener lister) {
        lister.onSuccess();
    }

}
