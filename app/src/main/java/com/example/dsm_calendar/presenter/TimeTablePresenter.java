package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.TimeTableContract;
import com.example.dsm_calendar.data.DTO.TimeTableUnit;
import com.example.dsm_calendar.data.TimeTableRepository;

import java.util.ArrayList;

public class TimeTablePresenter implements TimeTableContract.Presenter {

    private TimeTableContract.View timeTableView;
    private TimeTableContract.Repository timeTableRepo;

    public TimeTablePresenter(
            TimeTableContract.View timeTableView,
            TimeTableContract.Repository timeTableRepo
    ) {
        this.timeTableView = timeTableView;
        this.timeTableRepo = timeTableRepo;
    }

    @Override
    public void onStarted() {
        timeTableRepo.getTimeTable(new TimeTableRepository.GetTimeTableListener() {
            @Override
            public void onSuccess(ArrayList<TimeTableUnit> tableUnits) {
                timeTableView.getTimeTable(tableUnits);
            }

            @Override
            public void onFail(String message) {
                timeTableView.showMessageForLoadFail(message);
            }
        });
    }

    @Override
    public void onEditSaveClicked(ArrayList<TimeTableUnit> timeTableUnits) {
        timeTableRepo.editSave(timeTableUnits, new TimeTableRepository.EditSaveListener() {
            @Override
            public void onSuccess(ArrayList<TimeTableUnit> tableUnits) {
                timeTableView.getTimeTable(tableUnits);
                timeTableView.showMessageForEditSaveSuccess();
            }

            @Override
            public void onFail(String message) {
                timeTableView.showMessageForEditSaveFail(message);
            }
        });
    }
}
