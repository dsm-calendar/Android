package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MessageContract;
import com.example.dsm_calendar.data.DTO.Message;
import com.example.dsm_calendar.data.MessageRepository;

import java.util.ArrayList;

public class MessagePresenter implements MessageContract.Presenter {

    private MessageContract.View messageView;
    private MessageContract.Repository messageRepo;

    public MessagePresenter(
            MessageContract.View messageView,
            MessageContract.Repository messageRepo
    ){
        this.messageView = messageView;
        this.messageRepo = messageRepo;
    }

    @Override
    public void onClickItem(int messageId) {
        messageView.showInviteDialog(messageId);
    }

    @Override
    public void onLongClickItem(int messageId) {
        messageView.showDeleteDialog(messageId);
    }

    @Override
    public void onAcceptInviteClicked(int messageId) {
        messageRepo.acceptInvite(messageId, new MessageRepository.AcceptInviteListener() {
            @Override
            public void onSuccess() {
                messageView.showMessageForAcceptInviteSuccess();
            }

            @Override
            public void onFail(String message) {
                messageView.showMessageForAcceptInviteFail(message);
            }
        });
    }

    @Override
    public void onRejectInviteClicked(int messageId) {
        messageRepo.rejectInvite(messageId, new MessageRepository.RejectInviteListener() {
            @Override
            public void onSuccess() {
                messageView.showMessageForRejectInviteSuccess();
            }

            @Override
            public void onFail(String message) {
                messageView.showMessageForRejectInviteFail(message);
            }
        });
    }

    @Override
    public void onDeleteMessageClicked(int messageId) {
        messageRepo.deleteMessage(messageId, new MessageRepository.DeleteMessageListener() {
            @Override
            public void onSuccess() {
                messageView.showMessageForDeleteSuccess();
            }

            @Override
            public void onFail(String message) {
                messageView.showMessageForDeleteFail(message);
            }
        });
    }

    @Override
    public void onStarted() {
        messageRepo.getMessageList(new MessageRepository.GetMessageListListener() {
            @Override
            public void onSuccess(ArrayList<Message> messageList) {
                messageView.addItems(messageList);
            }

            @Override
            public void onFail(String message) {
                messageView.showMessageForLoadingFail(message);
            }
        });
    }
}
