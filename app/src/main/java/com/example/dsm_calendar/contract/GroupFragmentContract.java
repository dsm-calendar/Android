package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Room;
import com.example.dsm_calendar.data.GroupFragmentRepository;

import java.util.ArrayList;

public interface GroupFragmentContract {
    interface View{
        void showGroupMenuDialog(Room room, int position);
        void startGroupActivity(Room room);
        void showMessageForAddGroupSuccess();
        void showMessageForDeleteGroupSuccess();
        void showMessageForGetGroupListFail(String message);
        void showMessageForAddGroupFail(String message);
        void showMessageForDeleteGroupFail(String message);
        void addItems(ArrayList<Room> rooms);
        void deleteGroup(int position);
    }

    interface Presenter{
        void onClickItems(Room room);
        void onClickItemMenu(Room room, int position);
        void onStarted();
        void onAddGroup(Room room);
        void onDeleteGroup(int roomId, int position);
    }

    interface Repository{
        void getGroupList(GroupFragmentRepository.GetGroupListListener listener);
        void addGroup(Room room, GroupFragmentRepository.AddGroupListener listener);
        void deleteGroup(int roomId, GroupFragmentRepository.DeleteGroupListener listener);
    }
}
