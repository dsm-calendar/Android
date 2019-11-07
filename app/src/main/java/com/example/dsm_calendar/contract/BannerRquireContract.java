package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.BannerRequireRepository;

public interface BannerRquireContract {
    interface View{
        void showMessageForBannerRequireSuccess();
        void showMessageForBannerRequireFail(String message);
        void finishActivity();
    }

    interface Presenter{
        void onRequireClicked();
    }

    interface Repository{
        void requireBanner(BannerRequireRepository.RequireBannerListener listener);
    }
}
