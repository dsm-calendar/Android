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
    public void onClickLogout() {

    }
}
