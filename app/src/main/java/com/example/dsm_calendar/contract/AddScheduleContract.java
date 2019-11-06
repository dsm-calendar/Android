package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.AddScheduleRepository;

public interface AddScheduleContract {
    interface View{
        void showMessageForSuccess();
        void showMessageForFail(String message);
    }

    interface Presenter{
        void onSaveClicked(String title, String content, String startDay, String endDay);
    }

    interface Repository{
        void addSchedule(String title, String content, String startDay, String endDay, AddScheduleRepository.AddScheduleListener lister);
    }
}