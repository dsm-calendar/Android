package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.GroupMemberContract;
import com.example.dsm_calendar.data.DTO.RoomMember;
import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.DTO.User;
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
    public void onStarted() {
        groupMemberRepo.getMemberList(new GroupMemberRepository.GetMemberListListener() {
            @Override
            public void onSuccess(ArrayList<RoomMember> members) {
                groupMemberView.addItems(members);
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public void onInviteClicked(String userId, int roomId) {
        groupMemberRepo.inviteMember(roomId, userId, new GroupMemberRepository.InviteMemberListener() {
            @Override
            public void onSuccess() {
                groupMemberView.dismissInviteDialog();
                groupMemberView.showMessageForInviteSuccess();
            }

            @Override
            public void onFail(String message) {
                groupMemberView.showMessageForInviteFail(message);
            }
        });
    }

    @Override
    public void onMemberKickClicked() {
        groupMemberRepo.kickMember(new GroupMemberRepository.KickMemberListener() {
            @Override
            public void onSuccess() {
                groupMemberView.showMessageForKickSuccess();
            }

            @Override
            public void onFail(String message) {
                groupMemberView.showMessageForKickFail(message);
            }
        });
    }

    @Override
    public void onMemberAuthChanged(int authCode, int roomId) {
        groupMemberRepo.changeMemberAuth(roomId, authCode, new GroupMemberRepository.ChangeMemberAuthListener() {
            @Override
            public void onSuccess() {
                groupMemberView.dismissGroupMemberAuthDialog();
                groupMemberView.showMessageForAuthChangeSuccess(authCode);
            }

            @Override
            public void onFail(String message) {
                groupMemberView.showMessageForAuthChangeFail(message);
            }
        });
    }
}
