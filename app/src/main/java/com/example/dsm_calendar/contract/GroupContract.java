package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.GroupRepository;

import java.util.ArrayList;

public interface GroupContract {
    interface View{
        void showGroupAddDialog();
        void showGroupMenuDialog(String name);
        void startGroupActivity(String name);
        void addItems(ArrayList<String> testGroup);
    }

    interface Presenter{
        void onClickAddGroup();
        void onClickItems(String name);
        void onClickItemMenu(String name);
        void onStarted();
    }

    interface Repository{
        void getGroupList(GroupRepository.GetGroupListListener listener);
    }
}
