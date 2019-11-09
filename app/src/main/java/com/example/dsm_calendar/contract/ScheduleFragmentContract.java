package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleFragmentRepository;

import java.util.ArrayList;

public interface ScheduleFragmentContract {

    interface View{
        void showMessageForDeleteSchedule();
        void getItems(ArrayList<SampleSchedule> testSchedule);
        void deleteSchedule(int position);
    }

    interface Presenter{
        void onStarted();
        void onItemDeleteClicked(int index);
    }

    interface Repository{
        void getScheduleList(ScheduleFragmentRepository.GetScheduleListListener listener);
        void deleteSchedule(ScheduleFragmentRepository.DeleteScheduleListener listener);
    }
}
