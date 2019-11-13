package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.GroupScheduleContract;
import com.example.dsm_calendar.data.GroupScheduleRepository;
import com.example.dsm_calendar.data.Schedule;

import java.util.ArrayList;

public class GroupSchedulePresenter implements GroupScheduleContract.Presenter {

    private GroupScheduleContract.View groupScheduleView;
    private GroupScheduleContract.Repository groupScheduleRepo;

    public GroupSchedulePresenter(
            GroupScheduleContract.View groupScheduleView,
            GroupScheduleContract.Repository groupScheduleRepo
    ) {
        this.groupScheduleView = groupScheduleView;
        this.groupScheduleRepo = groupScheduleRepo;
    }

    @Override
    public void onStarted() {
        groupScheduleRepo.getSchedule(new GroupScheduleRepository.GetScheduleListener() {
            @Override
            public void onSuccess(ArrayList<Schedule> schedules) {
                groupScheduleView.getList(schedules);
            }

            @Override
            public void onFail(String message) {
                groupScheduleView.showMessageForGetScheduleFail(message);
            }
        });
    }

    @Override
    public void onItemDeleteClicked(int index) {
        groupScheduleRepo.deleteSchedule(new GroupScheduleRepository.DeleteScheduleListener() {
            @Override
            public void onSuccess() {
                groupScheduleView.showMessageForDeleteSuccess();
            }

            @Override
            public void onFail(String message) {
                groupScheduleView.showMessageForDeleteFail(message);
            }
        });
    }
}
