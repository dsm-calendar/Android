package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.MainRepository;

public interface MainContract {
    interface View{
        void setUserInfo(String id, int classOf, int iconIndex);
        void showMessageForGetUserInfoFail();
        void logout();
        void showMessageForLogoutFail(String message);
        void showMessageForChangeProfileFail(String message);
        void setProfileImage(int iconIndex);
    }

    interface Presenter {
        void onClickLogout();
        void onStarted();
        void onProfileChanged(Student student);
    }

    interface Repository{
        void getUserInfo(MainRepository.GetUserInfoListener listener);
        void changeProfile(Student student, MainRepository.ChangeProfileListener listener);
        void logout(MainRepository.LogoutListener listener);
    }
}
