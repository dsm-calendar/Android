package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.BannerRquireContract;
import com.example.dsm_calendar.data.BannerRequireRepository;

public class BannerRequirePresenter implements BannerRquireContract.Presenter {

    private BannerRquireContract.View bannerRequireView;
    private BannerRquireContract.Repository bannerRequireRepo;

    public BannerRequirePresenter(
            BannerRquireContract.View bannerRequireView,
            BannerRquireContract.Repository bannerRequireRepo
    ){
        this.bannerRequireView = bannerRequireView;
        this.bannerRequireRepo = bannerRequireRepo;
    }

    @Override
    public void onRequireClicked(String eventDetail, String eventPoster, String startDate, String endDate) {
        bannerRequireRepo.requireBanner(eventDetail, eventPoster, startDate, endDate, new BannerRequireRepository.RequireBannerListener() {
            @Override
            public void onSuccess() {
                bannerRequireView.showMessageForBannerRequireSuccess();
                bannerRequireView.finishActivity();
            }

            @Override
            public void onFail(String message) {
                bannerRequireView.showMessageForBannerRequireFail(message);
            }
        });
    }
}
