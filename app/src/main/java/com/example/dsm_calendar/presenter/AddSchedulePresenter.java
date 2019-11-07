package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.AddScheduleContract;
import com.example.dsm_calendar.data.AddScheduleRepository;
import com.example.dsm_calendar.ui.activity.AddScheduleActivity;

public class AddSchedulePresenter implements AddScheduleContract.Presenter {

    private AddScheduleActivity addScheduleActivity;
    private AddScheduleRepository addScheduleRepository;

    public AddSchedulePresenter(
            AddScheduleActivity activity,
            AddScheduleRepository repository
    ) {
        this.addScheduleActivity = activity;
        this.addScheduleRepository = repository;
    }

    @Override
    public void onSaveClicked(String title, String content, String startDay, String endDay) {
        addScheduleRepository.addSchedule(title, content, startDay, endDay, new AddScheduleRepository.AddScheduleListener() {
            @Override
            public void onSuccess() {
                addScheduleActivity.showMessageForSuccess();
                addScheduleActivity.finishActivity();
            }

            @Override
            public void onFail(String message) {
                addScheduleActivity.showMessageForFail(message);
            }
        });
    }
}
