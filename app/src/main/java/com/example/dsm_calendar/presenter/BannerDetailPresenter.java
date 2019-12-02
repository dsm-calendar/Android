package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.BannerDetailContract;
import com.example.dsm_calendar.data.BannerDetailRepository;
import com.example.dsm_calendar.data.DTO.Event;

public class BannerDetailPresenter implements BannerDetailContract.Presenter {

    private BannerDetailContract.View bannerDetailView;
    private BannerDetailContract.Repository bannerDetailRepo;

    public BannerDetailPresenter(
            BannerDetailContract.View bannerDetailView,
            BannerDetailContract.Repository bannerDetailRepo
    ) {
        this.bannerDetailView = bannerDetailView;
        this.bannerDetailRepo = bannerDetailRepo;
    }

    @Override
    public void onStarted(int eventId) {
        bannerDetailRepo.getEvent(eventId, new BannerDetailRepository.GetEventListener() {
            @Override
            public void onSuccess(Event event) {
                bannerDetailView.loadEvent(event);
            }

            @Override
            public void onFail(String message) {
                bannerDetailView.showMessageForLoadingFail(message);
            }
        });
    }
}
