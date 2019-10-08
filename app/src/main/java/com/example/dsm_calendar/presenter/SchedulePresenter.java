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
                scheduleView.getItems(testSchedule);
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onItemDeleteClicked(int index) {
        scheduleRepo.deleteSchedule(new ScheduleRepository.DeleteScheduleListener() {
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

    @Override
    public void onAddSchedule(String title, String date, String content) {
        scheduleRepo.addSchedule(title, date, content, new ScheduleRepository.AddScheduleListener() {
            @Override
            public void onSuccess() {
                scheduleView.showMessageForItemAdded();
                scheduleView.addSchedule(new SampleSchedule(title, date, content));
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onAddScheduleClicked(String date) {
        scheduleView.showScheduleAddDialog(date);
    }

}
