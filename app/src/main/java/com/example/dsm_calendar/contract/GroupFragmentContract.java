package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.GroupFragmentRepository;

import java.util.ArrayList;

public interface GroupFragmentContract {
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
        void getGroupList(GroupFragmentRepository.GetGroupListListener listener);
    }
}
