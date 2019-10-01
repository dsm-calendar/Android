package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MessageContract;
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
    public void onClickItem() {
        messageView.showInviteDialog();
    }

    @Override
    public void onLongClickItem() {
        messageView.showDeleteDialog();
    }

    @Override
    public void onStarted() {
        messageRepo.getMessageList(new MessageRepository.GetMessageListListener() {
            @Override
            public void onSuccess(ArrayList<String> testMessage, ArrayList<String> testDate) {
                messageView.addItems(testMessage, testDate);
            }

            @Override
            public void onFail() {

            }
        });
    }
}
