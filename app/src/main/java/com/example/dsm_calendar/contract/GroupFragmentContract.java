package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Room;
import com.example.dsm_calendar.data.GroupFragmentRepository;

import java.util.ArrayList;

public interface GroupFragmentContract {
    interface View{
        void showGroupMenuDialog(Room room);
        void startGroupActivity(Room room);
        void showMessageForGetGroupListFail(String message);
        void addItems(ArrayList<Room> rooms);
    }

    interface Presenter{
        void onClickItems(Room room);
        void onClickItemMenu(Room room);
        void onStarted();
    }

    interface Repository{
        void getGroupList(GroupFragmentRepository.GetGroupListListener listener);
    }
}
