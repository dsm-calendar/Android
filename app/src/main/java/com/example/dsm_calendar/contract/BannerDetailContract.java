package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.BannerDetailRepository;
import com.example.dsm_calendar.data.DTO.Event;

public interface BannerDetailContract {
    interface View{
        void loadEvent(Event event);
        void showMessageForLoadingFail(String message);
    }

    interface Presenter{
        void onStarted(int eventId);
    }

    interface Repository{
        void getEvent(int eventId, BannerDetailRepository.GetEventListener listener);
    }
}
