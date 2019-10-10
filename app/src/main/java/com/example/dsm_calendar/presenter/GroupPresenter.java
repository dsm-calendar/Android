package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.GroupContract;
import com.example.dsm_calendar.data.GroupRepository;

import java.util.ArrayList;

public class GroupPresenter implements GroupContract.Presenter {

    private GroupContract.View groupView;
    private GroupContract.Repository groupRepo;

    public GroupPresenter(
            GroupContract.View groupView,
            GroupContract.Repository groupRepo
    ){
        this.groupView = groupView;
        this.groupRepo = groupRepo;
    }

    @Override
    public void onClickAddGroup() {
        groupView.showGroupAddDialog();
    }

    @Override
    public void onClickItems() {
        groupView.showTestDialog();
    }

    @Override
    public void onStarted(ArrayList<String> testGroup) {
        groupRepo.getGroupList(new GroupRepository.GetGroupListListener() {
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
