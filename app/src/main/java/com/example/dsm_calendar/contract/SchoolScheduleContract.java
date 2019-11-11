package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.SchoolScheduleRepository;

import java.util.ArrayList;

public interface SchoolScheduleContract {
    interface View{
        void showMessageForDeleteSuccess();
        void showMessageForDeleteFail(String message);
        void showMessageForGetScheduleFail(String message);
        void getItems(ArrayList<SampleSchedule> list);
        void deleteSchedule(int position);
    }

    interface Presenter{
        void onStarted();
        void onDeleteClicked(int index);
    }

    interface Repository{
        void getSchedules(SchoolScheduleRepository.GetSchedulesListener listener);
        void deleteSchedule(SchoolScheduleRepository.DeleteScheduleListener listener);
    }
}
