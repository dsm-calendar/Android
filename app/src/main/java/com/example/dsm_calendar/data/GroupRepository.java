package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.GroupContract;

import java.util.ArrayList;

public class GroupRepository implements GroupContract.Repository {

    public interface GetGroupListListener{
        void onSuccess(ArrayList<String> testGroup);
        void onFail();
    }

    @Override
    public void getGroupList(GetGroupListListener listener) {
        ArrayList<String> testGroup = new ArrayList<>();

        testGroup.add("동휘와 함께하는 게임 만들기");
        testGroup.add("윤성이와 함께하는 디자인");
        testGroup.add("승민이와 함께하는 안드로이드");
        testGroup.add("하경이와 함께하는 서버만들기");
        testGroup.add("담임쌤과 함께하는 \"코아\"개념 배우기");
        testGroup.add("희명이와 함께하는 탈모갤러리");
        testGroup.add("민트니스가 함께하는 근성장 팩토리");
        testGroup.add("채홍이와 함께하는 인생파탄내기");
        testGroup.add("이제는 쓸게없어 막쓰는 그룹");
        testGroup.add("hello world!");
        testGroup.add("Tlqkf");

        listener.onSuccess(testGroup);
    }
}
