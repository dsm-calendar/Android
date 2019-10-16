package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Student;
import com.example.dsm_calendar.data.GroupMemberRepository;

import java.util.ArrayList;

public interface GroupMemberContract {

    interface View{
        void showGroupMemberDetailDialog();
        void finishActivity();
        void addItems(ArrayList<Student> students);
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
