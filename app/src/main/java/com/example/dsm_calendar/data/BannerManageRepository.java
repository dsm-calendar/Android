package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.BannerManageContract;

public class BannerManageRepository implements BannerManageContract.Repository {

    @Override
    public void getBanners(GetBannerListener listener) {

    }

    @Override
    public void addBanners(int id, AddBannerListener listener) {

    }

    @Override
    public void deleteBanners(int id, DeleteBannerListener listener) {

    }

    public interface GetBannerListener {
        void onSuccess();
        void onFail(String message);
    }

    public interface AddBannerListener {
        void onSuccess();
        void onFail(String message);
    }

    public interface DeleteBannerListener {
        void onSuccess();
        void onFail(String message);
    }

}
