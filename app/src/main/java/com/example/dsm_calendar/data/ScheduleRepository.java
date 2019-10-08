package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.ScheduleContract;

import java.util.ArrayList;

public class ScheduleRepository implements ScheduleContract.Repository {

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
        testSchedule.add(new SampleSchedule("sample 1", "2019-03-04", "blablablabla"));
        testSchedule.add(new SampleSchedule("sample 2", "2019-04-23", "today i have to go to school i want to go home fuck"));
        testSchedule.add(new SampleSchedule("sample 3", "2019-05-05", "holiday"));

        listener.onSuccess(testSchedule);
    }

    @Override
    public void addSchedule(String title, String date, String content, AddScheduleListener listener) {
        //TODO: server communication here

        listener.onSuccess();
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {
        //TODO: server communication here

        listener.onSuccess();
    }
}
