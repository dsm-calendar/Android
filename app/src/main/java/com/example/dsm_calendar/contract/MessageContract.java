package com.example.dsm_calendar.contract;

public interface MessageContract {
    interface View{
        void showInviteDialog();
        void showDeleteDialog();
    }

    interface Presenter{
        void onClickItem();
        void onLongClickItem();
    }

    interface Repository{
        void getMessageList();
    }
}
