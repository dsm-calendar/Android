package com.example.dsm_calendar.data;

import android.content.Context;
import android.content.SharedPreferences;

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
    public void saveUserData(LoginUserInfo user) {
        SharedPreferences userInfo = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =   userInfo.edit();
        editor.putInt("userID", user.getLoginUserId());
        editor.putString("ID", user.getId());
        editor.putInt("classOf", user.getClassOf());
        editor.putInt("iconIndex", user.getIconIndex());
        editor.putInt("myCalendarId", user.getMyCalendarId());
        editor.apply();
    }
}
