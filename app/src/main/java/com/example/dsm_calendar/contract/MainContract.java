package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MainRepository;

public interface MainContract {
    interface View{

    }

    interface Presenter {
        void onClickLogout();
        void onStarted();
    }

    interface Repository{
        void getUserInfo(MainRepository.GetUserInfoListener listener);
    }
}
