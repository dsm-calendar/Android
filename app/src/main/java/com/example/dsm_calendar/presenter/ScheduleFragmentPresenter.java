package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.ScheduleFragmentContract;
import com.example.dsm_calendar.data.DTO.Schedule;
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
            public void onSuccess(ArrayList<Schedule> testSchedule) {
                scheduleView.getItems(testSchedule);
            }

            @Override
            public void onFail(String message) {
                scheduleView.showMessageForGetScheduleFail(message);
            }
        });
    }

    @Override
    public void onItemDeleteClicked(int scheduleId) {
        scheduleRepo.deleteSchedule(scheduleId, new ScheduleFragmentRepository.DeleteScheduleListener() {
            @Override
            public void onSuccess() {
                scheduleView.deleteSchedule(scheduleId);
                scheduleView.showMessageForDeleteSuccess();
            }

            @Override
            public void onFail(String message) {
                scheduleView.showMessageForDeleteFail(message);
            }
        });
    }
}
