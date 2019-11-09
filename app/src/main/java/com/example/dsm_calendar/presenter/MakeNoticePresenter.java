package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.MakeNoticeContract;
import com.example.dsm_calendar.data.MakeNoticeRepository;

public class MakeNoticePresenter implements MakeNoticeContract.Presenter {

    private MakeNoticeContract.View makeNoticeView;
    private MakeNoticeContract.Repository makeNoticeRepo;

    public MakeNoticePresenter(
            MakeNoticeContract.View makeNoticeView,
            MakeNoticeContract.Repository makeNoticeRepo
    ) {
        this.makeNoticeView = makeNoticeView;
        this.makeNoticeRepo = makeNoticeRepo;
    }

    @Override
    public void onMakeNoticeClicked(String title, String content) {
        makeNoticeRepo.makeNotice(title, content, new MakeNoticeRepository.MakeNoticeListener() {
            @Override
            public void onSuccess() {
                makeNoticeView.showMessageForMakeNoticeSuccess();
                makeNoticeView.finishActivity();
            }

            @Override
            public void onFail(String message) {
                makeNoticeView.showMessageForMakeNoticeFail(message);
            }
        });
    }
}
