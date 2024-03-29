package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.GroupFragmentContract;
import com.example.dsm_calendar.data.DTO.Room;
import com.example.dsm_calendar.data.GroupFragmentRepository;

import java.util.ArrayList;

public class GroupFragmentPresenter implements GroupFragmentContract.Presenter {

    private GroupFragmentContract.View groupView;
    private GroupFragmentContract.Repository groupRepo;

    public GroupFragmentPresenter(
            GroupFragmentContract.View groupView,
            GroupFragmentContract.Repository groupRepo
    ){
        this.groupView = groupView;
        this.groupRepo = groupRepo;
    }

    @Override
    public void onClickItems(Room room) {
        groupView.startGroupActivity(room);
    }

    @Override
    public void onClickItemMenu(Room room, int position) {
        groupView.showGroupMenuDialog(room, position);
    }

    @Override
    public void onStarted() {
        groupRepo.getGroupList(new GroupFragmentRepository.GetGroupListListener() {
            @Override
            public void onSuccess(ArrayList<Room> rooms) {
                groupView.addItems(rooms);
            }

            @Override
            public void onFail(String message) {
                groupView.showMessageForGetGroupListFail(message);
            }
        });
    }

    @Override
    public void onAddGroup(Room room) {
        groupRepo.addGroup(room, new GroupFragmentRepository.AddGroupListener() {
            @Override
            public void onSuccess(ArrayList<Room> rooms) {
                groupView.addItems(rooms);
                groupView.showMessageForAddGroupSuccess();
            }

            @Override
            public void onFail(String message) {
                groupView.showMessageForAddGroupFail(message);
            }
        });
    }

    @Override
    public void onDeleteGroup(int roomId, int position) {
        groupRepo.deleteGroup(roomId, new GroupFragmentRepository.DeleteGroupListener() {
            @Override
            public void onSuccess() {
                groupView.deleteGroup(position);
                groupView.showMessageForDeleteGroupSuccess();
            }

            @Override
            public void onFail(String message) {
                groupView.showMessageForDeleteGroupFail(message);
            }
        });
    }
}
