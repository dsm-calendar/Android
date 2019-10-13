package com.example.dsm_calendar.contract;

public interface GroupMemberContract {

    interface View{
        void showGroupMemberDetailDialog();
    }

    interface Presenter{
        void onClickDetail();
    }

    interface Repository{
        void getMemberList();
    }
}
