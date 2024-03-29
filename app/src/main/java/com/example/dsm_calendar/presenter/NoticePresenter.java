package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.NoticeContract;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.NoticeRepository;

import java.util.ArrayList;

public class NoticePresenter implements NoticeContract.Presenter {

    private NoticeContract.View noticeView;
    private NoticeContract.Repository noticeRepo;

    public NoticePresenter(
            NoticeContract.View noticeView,
            NoticeContract.Repository noticeRepo
    ){
        this.noticeView = noticeView;
        this.noticeRepo = noticeRepo;
    }

    @Override
    public void onStarted() {
        noticeRepo.getNoticeList(new NoticeRepository.GetNoticeListener() {
            @Override
            public void onSuccess(ArrayList<Notice> noticeList) {
                noticeView.addItems(noticeList);
            }

            @Override
            public void onFail(String message) {
                noticeView.showMessageForLoadingFail(message);
            }
        });
    }

    @Override
    public void onClickItem(String title, String content) {
        noticeView.startNoticeDetailActivity(title, content);
    }

    @Override
    public void onClickItemDelete(int noticeId) {
        noticeRepo.deleteNotice(noticeId, new NoticeRepository.DeleteNoticeListener() {
            @Override
            public void onSuccess() {
                noticeView.showMessageForDeleteNoticeSuccess();
            }

            @Override
            public void onFail(String message) {
                noticeView.showMessageForDeleteNoticeFail(message);
            }
        });
    }
}
