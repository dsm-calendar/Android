package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.AddScheduleRepository;

public interface AddScheduleContract {
    interface View{
        void showMessageForSuccess();
        void showMessageForFail(String message);
        void finishActivity();
    }

    interface Presenter{
        void onSaveClicked(String scheduleCode, String title, String content, String startDay, String endDay);
    }

    interface Repository{
        void addSchedule(String code, String title, String content, String startDay, String endDay, AddScheduleRepository.AddScheduleListener lister);
    }
}
