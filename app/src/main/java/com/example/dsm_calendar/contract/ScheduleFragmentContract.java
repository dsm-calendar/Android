package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.ScheduleFragmentRepository;

import java.util.ArrayList;

public interface ScheduleFragmentContract {

    interface View{
        void showMessageForDeleteSuccess();
        void showMessageForDeleteFail(String message);
        void showMessageForGetScheduleFail(String message);
        void getItems(ArrayList<Schedule> testSchedule);
        void deleteSchedule(int position);
    }

    interface Presenter{
        void onStarted();
        void onItemDeleteClicked(int calendarId);
    }

    interface Repository{
        void getScheduleList(ScheduleFragmentRepository.GetScheduleListListener listener);
        void deleteSchedule(int scheduleId, ScheduleFragmentRepository.DeleteScheduleListener listener);
    }
}
