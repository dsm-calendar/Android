package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.RoomMember;
import com.example.dsm_calendar.data.GroupMemberRepository;
import java.util.ArrayList;

public interface GroupMemberContract {

    interface View{
        void showGroupMemberDetailDialog(int memberId, int position);
        void addItems(ArrayList<RoomMember> members);
        void deleteItem(int index);
        void dismissInviteDialog();
        void dismissGroupMemberAuthDialog();
        void showMessageForInviteSuccess();
        void showMessageForInviteFail(String message);
        void showMessageForAuthChangeSuccess(int authCode);
        void showMessageForAuthChangeFail(String message);
        void showMessageForKickSuccess();
        void showMessageForKickFail(String message);
        void showMessageForGetMembersFail(String message);
    }

    interface Presenter{
        void onClickDetail(int memberId, int position);
        void onStarted(int roomId);
        void onInviteClicked(String userId, int roomId);
        void onMemberKickClicked(int roomId, int memberId, int index);
        void onMemberAuthChanged(int authCode, int roomId, int memberId);
    }

    interface Repository{
        void getMemberList(int roomId, GroupMemberRepository.GetMemberListListener listener);
        void inviteMember(int roomId, String user, GroupMemberRepository.InviteMemberListener listener);
        void changeMemberAuth(int roomId, int memberId, int authCode, GroupMemberRepository.ChangeMemberAuthListener listener);
        void kickMember(int roomId, int memberId, GroupMemberRepository.KickMemberListener listener);
    }
}
