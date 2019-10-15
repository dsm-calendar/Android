package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.SignUpContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpRepository implements SignUpContract.Repository {

    private String URL = "http://10.156.145.132:8080/";

    public interface SignUpListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void SignUp(int std_no, int iconIndex, String ID, String password, SignUpListener listener) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        Call<Void> call = retrofitService.signUp(std_no, iconIndex, ID, password);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else {
                    listener.onFail(Integer.toString(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
