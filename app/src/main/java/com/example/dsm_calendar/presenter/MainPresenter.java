package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MainContract;
import com.example.dsm_calendar.data.MainRepository;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;
    private MainContract.Repository mainRepo;

    public MainPresenter(
            MainContract.View mainView,
            MainContract.Repository mainRepo
    ){
        this.mainView = mainView;
        this.mainRepo = mainRepo;
    }


    @Override
    public void onClickMailbox() {
        mainView.startMailBoxActivity();
    }

    @Override
    public void onClickSetting() {

    }

    @Override
    public void onClickRequireEvent() {

    }

    @Override
    public void onClickTimeTable() {
        mainView.startTimeTableActivity();
    }

    @Override
    public void onClickSchoolCalendar() {

    }

    @Override
    public void onClickMyCalendar() {
        mainView.moveToMyCalendar();
    }

    @Override
    public void onClickLogout() {

    }

    @Override
    public void onStarted() {
        mainRepo.getUserInfo(new MainRepository.GetUserInfoListener() {
            @Override
            public void onSuccess(String id, int classOf, int iconIndex) {
                mainView.setUserInfo(id, classOf, iconIndex);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
