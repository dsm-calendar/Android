package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.GroupRepository;

import java.util.ArrayList;

public interface GroupContract {
    interface View{
        void showGroupAddDialog();
        void startGroupActivity();
        void addItems(ArrayList<String> testGroup);
    }

    interface Presenter{
        void onClickAddGroup();
        void onClickItems();
        void onStarted(ArrayList<String> testGroup);
    }

    interface Repository{
        void getGroupList(GroupRepository.GetGroupListListener listener);
    }
}
