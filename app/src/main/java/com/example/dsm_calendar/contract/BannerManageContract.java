package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.BannerManageRepository;

public interface BannerManageContract {
    interface View{
        void showBanners();
    }

    interface Presenter{
        void onStarted();
        void onAccept();
        void onReject();
    }

    interface Repository{
        void getBanners(BannerManageRepository.GetBannerListener listener);
        void addBanners(int id, BannerManageRepository.AddBannerListener listener);
        void deleteBanners(int id, BannerManageRepository.DeleteBannerListener listener);
    }
}
