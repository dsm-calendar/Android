package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.SchoolScheduleContract;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public class SchoolScheduleRepository implements SchoolScheduleContract.Repository {

    public interface GetSchedulesListener{
        void onSuccess(ArrayList<SampleSchedule> list);
        void onFail(String message);
    }

    public interface DeleteScheduleListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getSchedules(GetSchedulesListener listener) {
        ArrayList<SampleSchedule> list = new ArrayList<>();

        list.add((new SampleSchedule("sample title", "2019-11-1", "2019-11-1", "sample content",
                CalendarDay.from(2019, 10, 1), CalendarDay.from(2019, 10, 1))));
        list.add(new SampleSchedule("sample title", "2019-11-16", "2019-11-16", "sample content",
                CalendarDay.from(2019, 10, 16), CalendarDay.from(2019, 10, 16)));

        listener.onSuccess(list);
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {

    }
}
