package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.BannerManageContract;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerManageRepository implements BannerManageContract.Repository {

    private Context context;
    private int token;

    public BannerManageRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface GetBannerListener {
        void onSuccess(ArrayList<Event> banners);
        void onFail(String message);
    }

    public interface ManageBannerListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getBanners(GetBannerListener listener) {
        Call<ArrayList<Event>> call = CalendarRetrofit.getInstance().getService().getEventList(token);
        call.enqueue(new Callback<ArrayList<Event>>() {
            @Override
            public void onResponse(Call<ArrayList<Event>> call, Response<ArrayList<Event>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Event>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });

    }

    @Override
    public void manageBanners(int eventId, boolean eventStatus, ManageBannerListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().manageEvent(token, eventId, eventStatus);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
