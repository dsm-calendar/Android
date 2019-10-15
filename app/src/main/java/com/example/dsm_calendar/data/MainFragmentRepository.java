package com.example.dsm_calendar.data;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.MainFragmentContract;

import java.util.ArrayList;

public class MainFragmentRepository implements MainFragmentContract.Repository {

    public interface GetTodayScheduleListener{
        void onSuccess(ArrayList<String> todayList);
        void onFail();
    }

    public interface GetNoticeListListener{
        void onSuccess(ArrayList<String> noticeList);
        void onFail();
    }

    public interface GetBannerListener{
        void onSuccess(ArrayList<Integer> bannerList);
        void onFail();
    }

    public interface GetMyTimeTableListener{
        void onSuccess();
        void onFail();
    }

    @Override
    public void getTodaySchedule(GetTodayScheduleListener listener) {
        ArrayList<String> todayList = new ArrayList<>();

        todayList.add("do laundry");
        todayList.add("homework");
        todayList.add("buy some beer");
        todayList.add("sleep like a boss");
        todayList.add("buy a box of monster energy");
        todayList.add("goto bank");

        listener.onSuccess(todayList);
    }

    @Override
    public void getNoticeList(GetNoticeListListener listListener) {
        ArrayList<String> noticeList = new ArrayList<>();

        noticeList.add("sample");
        noticeList.add("sample2");
        noticeList.add("sample3");
        noticeList.add("long long long long long long sample");
        noticeList.add("short sample?");
        noticeList.add("sample what?");

        listListener.onSuccess(noticeList);
    }

    @Override
    public void getBanner(GetBannerListener listener) {
        ArrayList<Integer> bannerList = new ArrayList<>();

        bannerList.add(R.drawable.sample_sportscar);
        bannerList.add(R.drawable.sample_rainbow);
        bannerList.add(R.drawable.sample_ocean);
        bannerList.add(R.drawable.sample_universe);
        bannerList.add(R.drawable.sample_car);

        listener.onSuccess(bannerList);
    }

    @Override
    public void getMyTimeTable(GetMyTimeTableListener listener) {

    }
}
