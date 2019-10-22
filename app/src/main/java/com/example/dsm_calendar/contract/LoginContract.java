package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.LoginRepository;

public interface LoginContract {
    interface View{
        String getID();
        String getPassword();
        void startMainActivity();

        void showMessageForSuccess();
        void showMessageForFail(String message);
    }

    interface Presenter{
        void onClickConfirm();
    }

    interface Repository{
        void Login(String ID, String password, LoginRepository.LoginListener listener);
        void saveUserData(int userID);
    }
}
