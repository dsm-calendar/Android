package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.BannerManageContract;
import com.example.dsm_calendar.data.BannerManageRepository;
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
            public void onSuccess(ArrayList<SampleBanner> banners) {
                bannerManageView.addBannerList(banners);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    public void onAccept(int id, int position) {
        bannerManageRepo.addBanners(id, new BannerManageRepository.AddBannerListener() {
            @Override
            public void onSuccess() {
                bannerManageView.deleteBanner(position);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

    @Override
    public void onReject(int id, int position) {
        bannerManageRepo.deleteBanners(id, new BannerManageRepository.DeleteBannerListener() {
            @Override
            public void onSuccess() {
                bannerManageView.deleteBanner(position);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
