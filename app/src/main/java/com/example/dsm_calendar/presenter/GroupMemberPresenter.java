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
    public void onClickDetail(RoomMember member, int position) {
        groupMemberView.showGroupMemberDetailDialog(member, position);
    }

    @Override
    public void onStarted(int roomId) {
        groupMemberRepo.getMemberList(roomId, new GroupMemberRepository.GetMemberListListener() {
            @Override
            public void onSuccess(ArrayList<RoomMember> members) {
                groupMemberView.addItems(members);
            }

            @Override
            public void onFail(String message) {
                groupMemberView.showMessageForGetMembersFail(message);
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
    public void onMemberKickClicked(int roomId, RoomMember member, int index) {
        groupMemberRepo.kickMember(roomId, member, new GroupMemberRepository.KickMemberListener() {
            @Override
            public void onSuccess() {
                groupMemberView.showMessageForKickSuccess();
                groupMemberView.deleteItem(index);
            }

            @Override
            public void onFail(String message) {
                groupMemberView.showMessageForKickFail(message);
            }
        });
    }

    @Override
    public void onMemberAuthChanged(int authCode, int roomId, RoomMember member) {
        groupMemberRepo.changeMemberAuth(roomId, member, authCode, new GroupMemberRepository.ChangeMemberAuthListener() {
            @Override
            public void onSuccess(ArrayList<RoomMember> members) {
                groupMemberView.dismissGroupMemberAuthDialog();
                groupMemberView.showMessageForAuthChangeSuccess(authCode);
                groupMemberView.addItems(members);
            }

            @Override
            public void onFail(String message) {
                groupMemberView.showMessageForAuthChangeFail(message);
            }
        });
    }
}
