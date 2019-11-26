package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.BannerManageRepository;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.SampleBanner;

import java.util.ArrayList;

public interface BannerManageContract {
    interface View{
        void addBannerList(ArrayList<Event> banners);
        void manageBanner(int position, boolean eventStatus);
        void showMessageForBannerManageSuccess(boolean eventStatus);
        void showMessageForBannerManageFail(boolean eventStatus, String message);
    }

    interface Presenter{
        void onStarted();
        void onManageButtonClicked(int id, boolean eventStatus, int position);
    }

    interface Repository{
        void getBanners(BannerManageRepository.GetBannerListener listener);
        void manageBanners(int eventId, boolean eventStatus, BannerManageRepository.ManageBannerListener listener);
    }
}
