package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MainRepository;

public interface MainContract {
    interface View{
        void startMailBoxActivity();
        void startTimeTableActivity();
        void moveToMyCalendar();
        void setUserInfo(String id, int classOf, int iconIndex);
    }

    interface Presenter {
        void onClickMailbox();
        void onClickSetting();
        void onClickRequireEvent();
        void onClickTimeTable();
        void onClickSchoolCalendar();
        void onClickMyCalendar();
        void onClickLogout();
        void onStarted();
    }

    interface Repository{
        void getUserInfo(MainRepository.GetUserInfoListener listener);
    }
}
