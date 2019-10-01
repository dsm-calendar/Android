package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.GroupContract;

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
}
