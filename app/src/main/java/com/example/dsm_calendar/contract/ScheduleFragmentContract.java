package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleFragmentRepository;

import java.util.ArrayList;

public interface ScheduleFragmentContract {

    interface View{
        void showMessageForDeleteSchedule();
        void showMessageForItemAdded();
        void showMessageForSelectDate();
        void getItems(ArrayList<SampleSchedule> testSchedule);
        void addSchedule(SampleSchedule schedule);
        void deleteSchedule(int position);
    }

    interface Presenter{
        void onStarted();
        void onItemDeleteClicked(int index);
    }

    interface Repository{
        void getScheduleList(ScheduleFragmentRepository.GetScheduleListListener listener);
        void addSchedule(SampleSchedule schedule, ScheduleFragmentRepository.AddScheduleListener listener);
        void deleteSchedule(ScheduleFragmentRepository.DeleteScheduleListener listener);
    }
}
