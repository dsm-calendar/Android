package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.MessageContract;

import java.util.ArrayList;

public class MessageRepository implements MessageContract.Repository {

    private ArrayList<String> testMessage = new ArrayList<>();
    private ArrayList<String> testDate = new ArrayList<>();

    public interface GetMessageListListener{
        void onSuccess(ArrayList<String> testMessage, ArrayList<String> testDate);
        void onFail(String message);
    }

    public interface AcceptInviteListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface RejectInviteListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface DeleteMessageListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getMessageList(GetMessageListListener listener) {
        testMessage.add("동휘님이 게임만들기 프로젝트에 당신을 초대하셨습니다");
        testMessage.add("윤성님이 야구그룹에 당신을 초대하셨습니다");
        testMessage.add("승민님이 헬스장에 당신을 초대하셨습니다");
        testMessage.add("하경님이 이상한 곳에 당신을 초대하셨습니다");
        testMessage.add("누군가가 대마고에 당신을 초대하셨습니다");
        testMessage.add("경고: 당신은 사람입니다");
        testMessage.add("안녕하세요 dsm-calendar에 오신 것을 환영합니다");
        testDate.add("2019.01.03");
        testDate.add("2019.02.14");
        testDate.add("2019.02.22");
        testDate.add("2019.05.05");
        testDate.add("2019.05.10");
        testDate.add("2019.06.29");
        testDate.add("2019.07.02");
        listener.onSuccess(testMessage, testDate);
    }

    @Override
    public void acceptInvite(AcceptInviteListener listener) {
        listener.onSuccess();
    }

    @Override
    public void rejectInvite(RejectInviteListener listener) {
        listener.onSuccess();
    }

    @Override
    public void deleteMessage(DeleteMessageListener listener) {
        listener.onSuccess();
    }
}
