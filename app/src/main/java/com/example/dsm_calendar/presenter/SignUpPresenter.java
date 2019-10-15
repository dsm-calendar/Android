package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.SignUpContract;
import com.example.dsm_calendar.data.SignUpRepository;

public class SignUpPresenter implements SignUpContract.Presenter {

    private SignUpContract.View signInView;
    private SignUpContract.Repository signInRepo;

    public SignUpPresenter(
            SignUpContract.View signInView,
            SignUpContract.Repository signInRepo
    ){
        this.signInView = signInView;
        this.signInRepo = signInRepo;
    }

    @Override
    public void onClickSignUp() {
        int defaultIconIndex = 0;
        signInRepo.SignUp(signInView.getStd_no(), defaultIconIndex, signInView.getID(), signInView.getPassword(), new SignUpRepository.SignUpListener() {
            @Override
            public void onSuccess() {
                signInView.showMessageForSuccess();
            }

            @Override
            public void onFail(String message) {
                signInView.showMessageForFail(message);
            }
        });
    }
}
