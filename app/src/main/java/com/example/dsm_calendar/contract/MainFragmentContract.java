package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.MainResponse;
import com.example.dsm_calendar.data.MainFragmentRepository;

import java.util.ArrayList;

public interface MainFragmentContract {
    interface View{
        void setMainFragment(MainResponse response);
        void showMessageForLoadMainPageFail(String message);
        void startNoticeActivity();
        void moveToScheduleFragment();
    }

    interface Presenter{
        void onStarted();
        void onClickNoticeItem();
        void onClickScheduleItem();
    }

    interface Repository{
        void getMainResponse(MainFragmentRepository.GetMainResponseListener listener);
    }
}
