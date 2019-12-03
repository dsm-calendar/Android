package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.BannerManageRepository;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.SampleBanner;

import java.util.ArrayList;

public interface BannerManageContract {
    interface View{
        void addBannerList(ArrayList<Event> banners);
        void manageBanner(int position, boolean eventStatus);
        void showMessageForBannerLoadingFail(String message);
        void showMessageForBannerManageSuccess(boolean eventStatus);
        void showMessageForBannerManageFail(boolean eventStatus, String message);
    }

    interface Presenter{
        void onStarted();
        void onManageButtonClicked(Event event, boolean eventStatus, int position);
    }

    interface Repository{
        void getBanners(BannerManageRepository.GetBannerListener listener);
        void manageBanners(Event event, boolean eventStatus, BannerManageRepository.ManageBannerListener listener);
    }
}
