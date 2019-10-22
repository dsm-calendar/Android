package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.GroupMemberRepository;

import java.util.ArrayList;

public class GroupMemberPresenter implements GroupMemberContract.Presenter {

    private GroupMemberContract.View groupMemberView;
    private GroupMemberContract.Repository groupMemberRepo;

    public GroupMemberPresenter(
            GroupMemberContract.View groupMemberView,
            GroupMemberContract.Repository groupMemberRepo
    ){
       this.groupMemberView = groupMemberView;
       this.groupMemberRepo = groupMemberRepo;
    }

    @Override
    public void onClickDetail() {
        groupMemberView.showGroupMemberDetailDialog();
    }

    @Override
    public void onClickAdd() {
        //TODO: show memberAdd dialog
    }

    @Override
    public void onClickBack() {
        groupMemberView.finishActivity();
    }

    @Override
    public void onStarted() {

        groupMemberRepo.getMemberList(new GroupMemberRepository.GetMemberListListener() {
            @Override
            public void onSuccess(ArrayList<Student> students) {
                groupMemberView.addItems(students);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
