package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MainRepository;

public interface MainContract {
    interface View{
        void setUserInfo(String id, int classOf, int iconIndex);
        void onFailGetUserInfo();
    }

    interface Presenter {
        void onClickLogout();
        void onStarted();
    }

    interface Repository{
        void getUserInfo(MainRepository.GetUserInfoListener listener);
    }
}
