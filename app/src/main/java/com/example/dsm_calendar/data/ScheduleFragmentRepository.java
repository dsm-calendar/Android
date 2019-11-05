package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.ScheduleFragmentContract;

import java.util.ArrayList;

public class ScheduleFragmentRepository implements ScheduleFragmentContract.Repository {

    public interface GetScheduleListListener{
        void onSuccess(ArrayList<SampleSchedule> testSchedule);
        void onFail();
    }

    public interface AddScheduleListener{
        void onSuccess();
        void onFail();
    }

    public interface DeleteScheduleListener{
        void onSuccess();
        void onFail();
    }

    @Override
    public void getScheduleList(GetScheduleListListener listener) {
        ArrayList<SampleSchedule> testSchedule = new ArrayList<>();

        listener.onSuccess(testSchedule);
    }

    @Override
    public void addSchedule(SampleSchedule schedule, AddScheduleListener listener) {
        //TODO: server communication here

        listener.onSuccess();
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {
        //TODO: server communication here

        listener.onSuccess();
    }
}
