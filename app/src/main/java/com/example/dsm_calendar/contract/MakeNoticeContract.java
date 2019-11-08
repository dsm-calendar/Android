package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.MakeNoticeRepository;

public interface MakeNoticeContract  {
    interface View{
        void showMessageForMakeNoticeSuccess();
        void showMessageForMakeNoticeFail(String message);
        void finishActivity();
    }

    interface Presenter{
        void onMakeNoticeClicked(String title, String content);
    }

    interface Repository{
        void makeNotice(String title, String content, MakeNoticeRepository.MakeNoticeListener listener);
    }
}
