package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.GroupScheduleContract;

import java.util.ArrayList;

public class GroupScheduleRepository implements GroupScheduleContract.Repository {

    public interface GetScheduleListener{
        void onSuccess(ArrayList<SampleSchedule> schedules);
        void onFail(String message);
    }

    public interface DeleteScheduleListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getSchedule(GetScheduleListener listener) {
        ArrayList<SampleSchedule> sample = new ArrayList<>();

        listener.onSuccess(sample);
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {
        listener.onSuccess();
    }
}
