package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.NoticeRepository;

import java.util.ArrayList;

public interface NoticeContract {
    interface View {
        void addItems(ArrayList<Notice> noticeList);
        void startNoticeDetailActivity(String title, String content);
    }

    interface Presenter {
        void onStarted();
        void onClickItem(String title, String content);
    }

    interface Repository {
        void getNoticeList(NoticeRepository.GetNoticeListener listener);
    }
}
