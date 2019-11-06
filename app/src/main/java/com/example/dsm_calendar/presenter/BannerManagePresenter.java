package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.BannerManageContract;
import com.example.dsm_calendar.data.BannerManageRepository;

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
            public void onSuccess() {
                bannerManageView.showBanners();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    public void onAccept() {
        bannerManageRepo.addBanners(0, new BannerManageRepository.AddBannerListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    public void onReject() {
        bannerManageRepo.deleteBanners(0, new BannerManageRepository.DeleteBannerListener() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
