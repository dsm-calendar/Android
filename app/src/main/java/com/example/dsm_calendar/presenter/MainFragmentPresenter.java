package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MainFragmentContract;
import com.example.dsm_calendar.data.DTO.MainResponse;
import com.example.dsm_calendar.data.MainFragmentRepository;

import java.util.ArrayList;

public class MainFragmentPresenter implements MainFragmentContract.Presenter {

    private MainFragmentContract.View mainView;
    private MainFragmentContract.Repository mainRepo;

    public MainFragmentPresenter(
            MainFragmentContract.View mainView,
            MainFragmentContract.Repository mainRepo
    ){
        this.mainView = mainView;
        this.mainRepo = mainRepo;
    }

    @Override
    public void onStarted() {
        mainRepo.getMainResponse(new MainFragmentRepository.GetMainResponseListener() {
            @Override
            public void onSuccess(MainResponse response) {
                mainView.setMainFragment(response);
            }

            @Override
            public void onFail(String message) {
                mainView.showMessageForLoadMainPageFail(message);
            }
        });
    }

    @Override
    public void onClickNoticeItem() {
        mainView.startNoticeActivity();
    }

    @Override
    public void onClickScheduleItem() {
        mainView.moveToScheduleFragment();
    }
}
