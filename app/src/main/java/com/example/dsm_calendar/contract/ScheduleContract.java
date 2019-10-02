package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleRepository;

import java.util.ArrayList;

public interface ScheduleContract {

    interface View{
        void showScheduleAddDialog();
        void showMessageForItemClicked();
        void showMessageForDeleteSchedule();
        void addItems(ArrayList<SampleSchedule> testSchedule);
    }

    interface Presenter{
        void onStarted();
        void onItemClicked();
        void onItemDeleteClicked();
        void onAddScheduleClicked();
    }

    interface Repository{
        void getScheduleList(ScheduleRepository.GetScheduleListListener listener);
    }
}
