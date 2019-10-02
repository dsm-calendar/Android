package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.ScheduleContract;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleRepository;

import java.util.ArrayList;

public class SchedulePresenter implements ScheduleContract.Presenter {
    private ScheduleContract.View scheduleView;
    private ScheduleContract.Repository scheduleRepo;

    public SchedulePresenter(
            ScheduleContract.View scheduleView,
            ScheduleContract.Repository scheduleRepo
    ){
        this.scheduleView = scheduleView;
        this.scheduleRepo = scheduleRepo;
    }


    @Override
    public void onStarted() {
        scheduleRepo.getScheduleList(new ScheduleRepository.GetScheduleListListener() {
            @Override
            public void onSuccess(ArrayList<SampleSchedule> testSchedule) {
                scheduleView.addItems(testSchedule);
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onItemClicked() {

    }

    @Override
    public void onItemDeleteClicked() {
        scheduleView.showMessageForDeleteSchedule();
    }

    @Override
    public void onAddScheduleClicked() {
        scheduleView.showScheduleAddDialog();
    }
}
