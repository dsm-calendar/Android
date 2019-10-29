package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.GroupMemberRepository;
import java.util.ArrayList;

public interface GroupMemberContract {

    interface View{
        void showGroupMemberDetailDialog();
        void addItems(ArrayList<Student> students);
        void dismissInviteDialog();
        void dismissGroupMemberAuthDialog();
        void showMessageForInviteSuccess();
        void showMessageForInviteFail(String message);
        void showMessageForAuthChangeSuccess();
        void showMessageForAuthChangeFail(String message);
    }

    interface Presenter{
        void onClickDetail();
        void onStarted();
        void onInviteClicked(String ID);
        void onMemberAuthChanged(int authCode);
    }

    interface Repository{
        void getMemberList(GroupMemberRepository.GetMemberListListener listener);
        void inviteMember(GroupMemberRepository.InviteMemberListener listener);
        void changeMemberAuth(GroupMemberRepository.ChangeMemberAuthListener listener);
    }
}
