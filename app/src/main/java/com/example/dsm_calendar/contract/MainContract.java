package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MainRepository;

public interface MainContract {
    interface View{
        void setUserInfo(String id, int classOf, int iconIndex);
        void onFailGetUserInfo();
        void logout();
    }

    interface Presenter {
        void onClickLogout();
        void onStarted();
        void onProfileChanged(int iconIndex);
    }

    interface Repository{
        void getUserInfo(MainRepository.GetUserInfoListener listener);
        void changeProfile(int iconIndex, MainRepository.ChangeProfileListener listener);
        void logout();
    }
}
