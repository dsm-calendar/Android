package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.GroupFragmentContract;
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
    public void onClickAddGroup() {
        groupView.showGroupAddDialog();
    }

    @Override
    public void onClickItems(String name) {
        groupView.startGroupActivity(name);
    }

    @Override
    public void onClickItemMenu(String name) {
        groupView.showGroupMenuDialog(name);
    }

    @Override
    public void onStarted() {
        groupRepo.getGroupList(new GroupFragmentRepository.GetGroupListListener() {
            @Override
            public void onSuccess(ArrayList<String> testGroup) {
                groupView.addItems(testGroup);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
