package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleRepository;

import java.util.ArrayList;

public interface ScheduleContract {

    interface View{
        void showScheduleAddDialog(String date);
        void showMessageForDeleteSchedule();
        void showMessageForItemAdded();
        void getItems(ArrayList<SampleSchedule> testSchedule);
        void addSchedule(SampleSchedule schedule);
        void deleteSchedule(int position);
    }

    interface Presenter{
        void onStarted();
        void onItemDeleteClicked(int index);
        void onAddScheduleClicked(String date);
        void onAddSchedule(String title, String date, String content);
    }

    interface Repository{
        void getScheduleList(ScheduleRepository.GetScheduleListListener listener);
        void addSchedule(String title, String date, String content, ScheduleRepository.AddScheduleListener listener);
        void deleteSchedule(ScheduleRepository.DeleteScheduleListener listener);
    }
}
