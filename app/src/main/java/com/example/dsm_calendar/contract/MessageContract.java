package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MessageRepository;

import java.util.ArrayList;

public interface MessageContract {
    interface View{
        void showInviteDialog();
        void showDeleteDialog();
        void addItems(ArrayList<String> testMessage, ArrayList<String> testDate);
    }

    interface Presenter{
        void onClickItem();
        void onLongClickItem();
        void onStarted();
    }

    interface Repository{
        void getMessageList(MessageRepository.GetMessageListListener listener);
    }
}
