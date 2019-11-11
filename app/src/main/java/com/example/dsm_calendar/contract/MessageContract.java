package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MessageRepository;

import java.util.ArrayList;

public interface MessageContract {
    interface View{
        void showInviteDialog();
        void showDeleteDialog();
        void showMessageForAcceptInviteSuccess();
        void showMessageForAcceptInviteFail(String message);
        void showMessageForRejectInviteSuccess();
        void showMessageForRejectInviteFail(String message);
        void showMessageForDeleteSuccess();
        void showMessageForDeleteFail(String message);
        void addItems(ArrayList<String> testMessage, ArrayList<String> testDate);
    }

    interface Presenter{
        void onClickItem();
        void onLongClickItem();
        void onAcceptInviteClicked();
        void onRejectInviteClicked();
        void onDeleteMessageClicked();
        void onStarted();
    }

    interface Repository{
        void getMessageList(MessageRepository.GetMessageListListener listener);
        void acceptInvite(MessageRepository.AcceptInviteListener listener);
        void rejectInvite(MessageRepository.RejectInviteListener listener);
        void deleteMessage(MessageRepository.DeleteMessageListener listener);
    }
}
