package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.ScheduleContract;
import com.prolificinteractive.materialcalendarview.CalendarDay;

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

        testSchedule.add(new SampleSchedule("sample title", "2019-11-1", "sample content", CalendarDay.from(2019, 10, 1)));
        //month should be -1 to decorate correctly

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
