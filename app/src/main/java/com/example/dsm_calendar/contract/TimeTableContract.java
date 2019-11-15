package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.TimeTableRepository;

import java.util.ArrayList;

public interface TimeTableContract {
    interface View{
        void getTimeTable(ArrayList<TimeTableUnit> tableUnits);
        void showMessageForLoadFail(String message);
        void showMessageForEditSaveSuccess();
        void showMessageForEditSaveFail(String message);
    }

    interface Presenter{
        void onStarted();
        void onEditSaveClicked(ArrayList<TimeTableUnit> tableUnits);
    }

    interface Repository{
        void getTimeTable(TimeTableRepository.GetTimeTableListener listener);
        void editSave(ArrayList<TimeTableUnit> timeTableUnits, TimeTableRepository.EditSaveListener listener);
    }
}
