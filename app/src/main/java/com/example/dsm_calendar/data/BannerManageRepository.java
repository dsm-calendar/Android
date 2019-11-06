package com.example.dsm_calendar.data;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.BannerManageContract;

import java.util.ArrayList;

public class BannerManageRepository implements BannerManageContract.Repository {

    @Override
    public void getBanners(GetBannerListener listener) {
        ArrayList<SampleBanner> sample = new ArrayList<>();
        sample.add(new SampleBanner(R.drawable.sample_car, "asdjkasndasnkdjnakdnwunadjndwnadnadjk"));
        sample.add(new SampleBanner(R.drawable.sample_universe, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        sample.add(new SampleBanner(R.drawable.sample_ocean, "kokokokokokokokokokokokokokoko"));
        sample.add(new SampleBanner(R.drawable.sample_sportscar, "asdjkasndasnwifwenjvjenjdjk"));

        listener.onSuccess(sample);
    }

    @Override
    public void addBanners(int id, AddBannerListener listener) {
        listener.onSuccess();
    }

    @Override
    public void deleteBanners(int id, DeleteBannerListener listener) {
        listener.onSuccess();
    }

    public interface GetBannerListener {
        void onSuccess(ArrayList<SampleBanner> banners);
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
