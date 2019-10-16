package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.LoginContract;
import com.example.dsm_calendar.data.DTO.Login;
import com.example.dsm_calendar.data.DTO.LoginUserInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginRepository implements LoginContract.Repository {

    private String URL = "http://10.156.145.132:8080/";

    public interface LoginListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void Login(String ID, String password, LoginListener listener) {
        Login login = new Login(ID, password);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<LoginUserInfo> call = retrofitService.login(login);
        call.enqueue(new Callback<LoginUserInfo>() {
            @Override
            public void onResponse(Call<LoginUserInfo> call, Response<LoginUserInfo> response) {
                if (response.code() == 200){
                    listener.onSuccess();
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
}
