package com.example.dsm_calendar.contract;

public interface GroupContract {
    interface View{
        void showGroupAddDialog();
    }

    interface Presenter{
        void onClickAddGroup();
    }

    interface Repository{

    }
}
