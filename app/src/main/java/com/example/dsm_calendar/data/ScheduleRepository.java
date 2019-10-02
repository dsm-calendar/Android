package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.ScheduleContract;

import java.util.ArrayList;

public class ScheduleRepository implements ScheduleContract.Repository {

    private ArrayList<SampleSchedule> testSchedule = new ArrayList<>();

    public interface GetScheduleListListener{
        void onSuccess(ArrayList<SampleSchedule> testSchedule);
        void onFail();
    }

    @Override
    public void getScheduleList(GetScheduleListListener listener) {
        testSchedule.add(new SampleSchedule("sample 1", "2019-03-04", "blablablabla"));
        testSchedule.add(new SampleSchedule("sample 2", "2019-04-23", "today i have to go to school i want to go home fuck"));
        testSchedule.add(new SampleSchedule("sample 3", "2019-05-05", "holiday"));

        listener.onSuccess(testSchedule);
    }
}
