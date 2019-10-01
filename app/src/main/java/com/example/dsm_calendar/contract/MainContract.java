package com.example.dsm_calendar.contract;

public interface MainContract {
    interface View{
        void startMailBoxActivity();
        void showAuthDialog();
        void startTimeTableActivity();
        void moveToMyCalendar();
    }

    interface Presenter {
        void onClickMailbox();
        void onClickSetting();
        void onClickAuthCode();
        void onClickRequireEvent();
        void onClickTimeTable();
        void onClickSchoolCalendar();
        void onClickMyCalendar();
        void onClickLogout();
    }

    interface Repository{

    }
}
