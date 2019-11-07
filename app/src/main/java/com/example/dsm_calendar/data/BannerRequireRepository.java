package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.BannerRquireContract;

public class BannerRequireRepository implements BannerRquireContract.Repository {

    public interface RequireBannerListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void requireBanner(RequireBannerListener listener) {
        listener.onSuccess();
    }
}
