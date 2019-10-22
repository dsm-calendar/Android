package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MainFragmentRepository;

import java.util.ArrayList;

public interface MainFragmentContract {
    interface View{
        void getNotice(ArrayList<String> notices);
        void getSchedule(ArrayList<String> schedules);
        void getBanners(ArrayList<Integer> banners);
        void getMyTimeTable();
    }

    interface Presenter{
        void onStarted();
    }

    interface Repository{
        void getTodaySchedule(MainFragmentRepository.GetTodayScheduleListener listener);
        void getNoticeList(MainFragmentRepository.GetNoticeListListener listListener);
        void getBanner(MainFragmentRepository.GetBannerListener listener);
        void getMyTimeTable(MainFragmentRepository.GetMyTimeTableListener listener);
    }
}
