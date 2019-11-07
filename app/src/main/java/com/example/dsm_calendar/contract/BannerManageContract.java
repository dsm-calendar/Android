package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.BannerManageRepository;
import com.example.dsm_calendar.data.SampleBanner;

import java.util.ArrayList;

public interface BannerManageContract {
    interface View{
        void addBannerList(ArrayList<SampleBanner> banners);
        void deleteBanner(int position);
        void showMessageForBannerAcceptSuccess();
        void showMessageForBannerAcceptFail(String message);
        void showMessageForBannerRejectSuccess();
        void showMessageForBannerRejectFail(String message);
    }

    interface Presenter{
        void onStarted();
        void onAccept(int id, int position);
        void onReject(int id, int position);
    }

    interface Repository{
        void getBanners(BannerManageRepository.GetBannerListener listener);
        void addBanners(int id, BannerManageRepository.AddBannerListener listener);
        void deleteBanners(int id, BannerManageRepository.DeleteBannerListener listener);
    }
}
