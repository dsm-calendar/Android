package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MainContract;

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
}
