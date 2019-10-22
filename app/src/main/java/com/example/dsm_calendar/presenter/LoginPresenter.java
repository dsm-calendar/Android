package com.example.dsm_calendar.presenter;

import com.example.dsm_calendar.contract.LoginContract;
import com.example.dsm_calendar.data.LoginRepository;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private LoginContract.Repository loginRepo;

    public LoginPresenter(
            LoginContract.View loginView,
            LoginContract.Repository loginRepo
    ){
        this.loginView = loginView;
        this.loginRepo = loginRepo;
    }

    @Override
    public void onClickConfirm() {
        loginRepo.Login(loginView.getID(), loginView.getPassword(), new LoginRepository.LoginListener() {
            @Override
            public void onSuccess() {
                loginView.showMessageForSuccess();
                loginView.startMainActivity();
            }

            @Override
            public void onFail(String message) {
                loginView.showMessageForFail(message);
            }
        });
    }
}
