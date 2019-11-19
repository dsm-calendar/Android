package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.SchoolScheduleContract;
import com.example.dsm_calendar.data.DTO.Schedule;

import java.util.ArrayList;

public class SchoolScheduleRepository implements SchoolScheduleContract.Repository {

    public interface GetSchedulesListener{
        void onSuccess(ArrayList<Schedule> list);
        void onFail(String message);
    }

    public interface DeleteScheduleListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getSchedules(GetSchedulesListener listener) {
        
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {
        listener.onSuccess();
    }
}
