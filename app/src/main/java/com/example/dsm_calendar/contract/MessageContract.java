package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Message;
import com.example.dsm_calendar.data.MessageRepository;

import java.util.ArrayList;

public interface MessageContract {
    interface View{
        void showInviteDialog(int messageId);
        void showDeleteDialog(int messageId);
        void showMessageForAcceptInviteSuccess();
        void showMessageForAcceptInviteFail(String message);
        void showMessageForRejectInviteSuccess();
        void showMessageForRejectInviteFail(String message);
        void showMessageForDeleteSuccess();
        void showMessageForDeleteFail(String message);
        void showMessageForLoadingFail(String message);
        void addItems(ArrayList<Message> messageList);
    }

    interface Presenter{
        void onClickItem(int messageId);
        void onLongClickItem(int messageId);
        void onAcceptInviteClicked();
        void onRejectInviteClicked();
        void onDeleteMessageClicked(int messageId);
        void onStarted();
    }

    interface Repository{
        void getMessageList(MessageRepository.GetMessageListListener listener);
        void acceptInvite(MessageRepository.AcceptInviteListener listener);
        void rejectInvite(MessageRepository.RejectInviteListener listener);
        void deleteMessage(int messageId, MessageRepository.DeleteMessageListener listener);
    }
}
