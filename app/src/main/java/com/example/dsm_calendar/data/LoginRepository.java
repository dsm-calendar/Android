package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.LoginContract;
import com.example.dsm_calendar.data.DTO.Login;
import com.example.dsm_calendar.data.DTO.LoginUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository implements LoginContract.Repository {

    private Context context;

    public interface LoginListener{
        void onSuccess();
        void onFail(String message);
    }

    public interface CheckLoginListener{
        void onLogin();
        void onLogout();
    }

    public LoginRepository(Context context){
        this.context = context;
    }

    @Override
    public void Login(String ID, String password, LoginListener listener) {
        Login login = new Login(ID, password);

        Call<LoginUserInfo> call = CalendarRetrofit.getInstance().getService().login(login);
        call.enqueue(new Callback<LoginUserInfo>() {
            @Override
            public void onResponse(Call<LoginUserInfo> call, Response<LoginUserInfo> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                    saveUserData(response.body());
                } else if(response.code() == 404) {
                    listener.onFail("계정을 찾을 수 없습니다.");
                } else {
                    listener.onFail(Integer.toString(response.code()));
                }
            }

            @Override
            public void onFailure(Call<LoginUserInfo> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void checkLogIn(CheckLoginListener listener) {
        UserPreference preference = UserPreference.getInstance(context);
        String ID = preference.getID();
        if (ID.equals("")){
            listener.onLogout();
        } else{
            listener.onLogin();
        }
    }

    @Override
    public void saveUserData(LoginUserInfo user) {
        UserPreference preference = UserPreference.getInstance(context);
        preference.putUserID("userID", user.getLoginUserId());
        preference.putID("ID", user.getId());
        preference.putClassOf("classOf", user.getClassOf());
        preference.putIconIndex("iconIndex", user.getIconIndex());
        preference.putMyCalendarID("myCalendarID", user.getMyCalendarId());
    }
}
