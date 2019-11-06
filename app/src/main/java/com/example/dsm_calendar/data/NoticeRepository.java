package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.NoticeContract;
import com.example.dsm_calendar.data.DTO.Notice;

import java.util.ArrayList;

public class NoticeRepository implements NoticeContract.Repository {

    private ArrayList<Notice> noticeList = new ArrayList<>();

    public interface GetNoticeListener{
        void onSuccess(ArrayList<Notice> noticeList);
        void onFail();
    }

    @Override
    public void getNoticeList(GetNoticeListener listener) {
        noticeList.add(new Notice("sample", "aldajksdnadkjanjkdniwnadjkw"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));
        noticeList.add(new Notice("sample??", "iqwdjnioqwnfoadmamwdkadmald"));

        listener.onSuccess(noticeList);
    }
}
