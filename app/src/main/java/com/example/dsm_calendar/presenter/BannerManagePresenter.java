package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.BannerManageContract;
import com.example.dsm_calendar.data.BannerManageRepository;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.SampleBanner;

import java.util.ArrayList;

public class BannerManagePresenter implements BannerManageContract.Presenter {

    private BannerManageContract.View bannerManageView;
    private BannerManageContract.Repository bannerManageRepo;

    public BannerManagePresenter(
            BannerManageContract.View bannerManageView,
            BannerManageContract.Repository bannerManageRepo
    ){
        this.bannerManageView = bannerManageView;
        this.bannerManageRepo = bannerManageRepo;
    }

    @Override
    public void onStarted() {
        bannerManageRepo.getBanners(new BannerManageRepository.GetBannerListener() {
            @Override
            public void onSuccess(ArrayList<Event> banners) {
                bannerManageView.addBannerList(banners);
            }

            @Override
            public void onFail(String message) {
                bannerManageView.showMessageForBannerLoadingFail(message);
            }
        });
    }

    @Override
    public void onManageButtonClicked(Event event, boolean eventStatus, int position) {
        bannerManageRepo.manageBanners(event, eventStatus, new BannerManageRepository.ManageBannerListener() {
            @Override
            public void onSuccess() {
                bannerManageView.showMessageForBannerManageSuccess(eventStatus);
                //TODO do something?
            }

            @Override
            public void onFail(String message) {
                bannerManageView.showMessageForBannerManageFail(eventStatus, message);
            }
        });
    }
}
