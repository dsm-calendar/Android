package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.R;
import com.example.dsm_calendar.contract.MainFragmentContract;
import com.example.dsm_calendar.data.DTO.MainResponse;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragmentRepository implements MainFragmentContract.Repository {

    private Context context;
    private int token;

    public interface GetMainResponseListener{
        void onSuccess(MainResponse response);
        void onFail(String message);
    }

    public MainFragmentRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    @Override
    public void getMainResponse(GetMainResponseListener listener) {
        Call<MainResponse> call = CalendarRetrofit.getInstance().getService().loadMainPage(token);
        call.enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if(response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
