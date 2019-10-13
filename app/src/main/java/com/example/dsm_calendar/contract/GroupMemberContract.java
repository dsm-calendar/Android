package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.GroupMemberRepository;
import com.example.dsm_calendar.data.SampleStudent;

import java.util.ArrayList;

public interface GroupMemberContract {

    interface View{
        void showGroupMemberDetailDialog();
        void finishActivity();
        void addItems(ArrayList<SampleStudent> students);
    }

    interface Presenter{
        void onClickDetail();
        void onClickAdd();
        void onClickBack();
        void onStarted();
    }

    interface Repository{
        void getMemberList(GroupMemberRepository.GetMemberListListener listener);
    }
}
