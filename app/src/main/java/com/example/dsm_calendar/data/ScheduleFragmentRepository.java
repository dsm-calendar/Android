package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.ScheduleFragmentContract;
import com.example.dsm_calendar.data.DTO.Schedule;

import java.util.ArrayList;

public class ScheduleFragmentRepository implements ScheduleFragmentContract.Repository {

    public interface GetScheduleListListener{
        void onSuccess(ArrayList<Schedule> testSchedule);
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
        ArrayList<Schedule> testSchedule = new ArrayList<>();

        testSchedule.add(new Schedule("sample title1", "2019-11-1", "2019-11-1", "sample content1"));
        testSchedule.add(new Schedule("sample title2", "2019-11-3", "2019-11-10", "sample content2"));
        testSchedule.add(new Schedule("sample title3", "2019-11-4", "2019-11-5", "sample content3"));
        testSchedule.add(new Schedule("sample title4", "2019-11-10", "2019-11-10", "sample content4"));
        //month should be -1 to decorate correctly

        listener.onSuccess(testSchedule);
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {
        //TODO: server communication here

        listener.onSuccess();
    }
}
