package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.BannerRequireRepository;

public interface BannerRquireContract {
    interface View{
        void showMessageForBannerRequireSuccess();
        void showMessageForBannerRequireFail(String message);
        void finishActivity();
    }

    interface Presenter{
        void onRequireClicked(String eventDetail, String eventPoster, String startDate, String endDate);
    }

    interface Repository{
        void requireBanner(String eventDetail, String eventPoster, String startDate, String endDate, BannerRequireRepository.RequireBannerListener listener);
    }
}
