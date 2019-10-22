package com.example.dsm_calendar.contract;

import com.example.dsm_calendar.data.SignUpRepository;

public interface SignUpContract {
    interface View{
        String getID();
        String getPassword();
        int getStd_no();

        void showMessageForSuccess();
        void showMessageForFail(String message);
    }

    interface Presenter{
        void onClickSignUp();
    }

    interface Repository{
        void SignUp(int std_no, String ID, String password, SignUpRepository.SignUpListener listener);
    }
}
