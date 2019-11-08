package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.SchoolScheduleContract;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.SchoolScheduleRepository;

import java.util.ArrayList;

public class SchoolSchedulePresenter implements SchoolScheduleContract.Presenter {

    private SchoolScheduleContract.View schoolScheduleView;
    private SchoolScheduleContract.Repository schoolScheduleRepo;

    public SchoolSchedulePresenter(
            SchoolScheduleContract.View schoolScheduleView,
            SchoolScheduleContract.Repository schoolScheduleRepo
    ) {
        this.schoolScheduleView = schoolScheduleView;
        this.schoolScheduleRepo = schoolScheduleRepo;
    }

    @Override
    public void onStarted() {
        schoolScheduleRepo.getSchedules(new SchoolScheduleRepository.GetSchedulesListener() {
            @Override
            public void onSuccess(ArrayList<SampleSchedule> list) {
                schoolScheduleView.getItems(list);
            }

            @Override
            public void onFail(String message) {
                schoolScheduleView.showMessageForGetScheduleFail(message);
            }
        });
    }

    @Override
    public void onDeleteClicked() {
        schoolScheduleRepo.deleteSchedule(new SchoolScheduleRepository.DeleteScheduleListener() {
            @Override
            public void onSuccess() {
                schoolScheduleView.showMessageForDeleteSuccess();
            }

            @Override
            public void onFail(String message) {
                schoolScheduleView.showMessageForDeleteFail(message);
            }
        });
    }
}