package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.TimeTableRepository;

public interface TimeTableContract {
    interface View{
        void setTimeTable();
        void showMessageForLoadFail(String message);
        void showMessageForEditSaveSuccess();
        void showMessageForEditSaveFail(String message);
    }

    interface Presenter{
        void onStarted();
        void onEditSaveClicked();
    }

    interface Repository{
        void getTimeTable(TimeTableRepository.GetTimeTableListener listener);
        void editSave(TimeTableRepository.EditSaveListener listener);
    }
}