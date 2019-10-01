package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MessageContract;

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
}
