package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.RoomMember;
import com.example.dsm_calendar.data.GroupMemberRepository;
import java.util.ArrayList;

public interface GroupMemberContract {

    interface View{
        void showGroupMemberDetailDialog();
        void addItems(ArrayList<RoomMember> members);
        void dismissInviteDialog();
        void dismissGroupMemberAuthDialog();
        void showMessageForInviteSuccess();
        void showMessageForInviteFail(String message);
        void showMessageForAuthChangeSuccess(int authCode);
        void showMessageForAuthChangeFail(String message);
        void showMessageForKickSuccess();
        void showMessageForKickFail(String message);
    }

    interface Presenter{
        void onClickDetail();
        void onStarted();
        void onInviteClicked(String userId, int roomId);
        void onMemberKickClicked();
        void onMemberAuthChanged(int authCode, int roomId);
    }

    interface Repository{
        void getMemberList(GroupMemberRepository.GetMemberListListener listener);
        void inviteMember(int roomId, String user, GroupMemberRepository.InviteMemberListener listener);
        void changeMemberAuth(int roomId, int authCode, GroupMemberRepository.ChangeMemberAuthListener listener);
        void kickMember(GroupMemberRepository.KickMemberListener listener);
    }
}
