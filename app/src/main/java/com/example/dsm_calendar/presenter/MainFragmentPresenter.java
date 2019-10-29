package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MainFragmentContract;
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
        mainRepo.getTodaySchedule(new MainFragmentRepository.GetTodayScheduleListener() {

            @Override
            public void onSuccess(ArrayList<String> todayList) {
                mainView.getSchedule(todayList);
            }

            @Override
            public void onFail() {
            mainView.showMessageForGetScheduleFail("msg");
            }
        });

        mainRepo.getNoticeList(new MainFragmentRepository.GetNoticeListListener() {
            @Override
            public void onSuccess(ArrayList<String> noticeList) {
                mainView.getNotice(noticeList);
            }

            @Override
            public void onFail() {
                mainView.showMessageForGetNoticeFail("msg");
            }
        });

        mainRepo.getBanner(new MainFragmentRepository.GetBannerListener() {
            @Override
            public void onSuccess(ArrayList<Integer> bannerList) {
                mainView.getBanners(bannerList);
            }

            @Override
            public void onFail() {
                mainView.ShowMessageForGetBannerFail("msg");
            }
        });

        mainRepo.getMyTimeTable(new MainFragmentRepository.GetMyTimeTableListener() {
            @Override
            public void onSuccess() {
                mainView.getMyTimeTable();
            }

            @Override
            public void onFail() {
                mainView.showMessageForGetTimeTableFail("msg");
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
