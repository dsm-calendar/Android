package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleRepository;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;

public interface ScheduleContract {

    interface View{
        void showScheduleAddDialog(String date, CalendarDay day);
        void showMessageForDeleteSchedule();
        void showMessageForItemAdded();
        void getItems(ArrayList<SampleSchedule> testSchedule);
        void addSchedule(SampleSchedule schedule);
        void deleteSchedule(int position);
    }

    interface Presenter{
        void onStarted();
        void onItemDeleteClicked(int index);
        void onAddScheduleClicked(String date, CalendarDay day);
        void onAddSchedule(SampleSchedule schedule);
    }

    interface Repository{
        void getScheduleList(ScheduleRepository.GetScheduleListListener listener);
        void addSchedule(SampleSchedule schedule, ScheduleRepository.AddScheduleListener listener);
        void deleteSchedule(ScheduleRepository.DeleteScheduleListener listener);
    }
}
