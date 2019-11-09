package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.MakeNoticeContract;

public class MakeNoticeRepository implements MakeNoticeContract.Repository {

    public interface MakeNoticeListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void makeNotice(String title, String content, MakeNoticeListener listener) {
        listener.onSuccess();
    }
}
