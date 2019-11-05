package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.ScheduleFragmentContract;
import com.example.dsm_calendar.data.SampleSchedule;
import com.example.dsm_calendar.data.ScheduleFragmentRepository;

import java.util.ArrayList;

public class ScheduleFragmentPresenter implements ScheduleFragmentContract.Presenter {
    private ScheduleFragmentContract.View scheduleView;
    private ScheduleFragmentContract.Repository scheduleRepo;

    public ScheduleFragmentPresenter(
            ScheduleFragmentContract.View scheduleView,
            ScheduleFragmentContract.Repository scheduleRepo
    ){
        this.scheduleView = scheduleView;
        this.scheduleRepo = scheduleRepo;
    }


    @Override
    public void onStarted() {
        scheduleRepo.getScheduleList(new ScheduleFragmentRepository.GetScheduleListListener() {
            @Override
            public void onSuccess(ArrayList<SampleSchedule> testSchedule) {
                scheduleView.getItems(testSchedule);
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onItemDeleteClicked(int index) {
        scheduleRepo.deleteSchedule(new ScheduleFragmentRepository.DeleteScheduleListener() {
            @Override
            public void onSuccess() {
                scheduleView.showMessageForDeleteSchedule();
                scheduleView.deleteSchedule(index);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
