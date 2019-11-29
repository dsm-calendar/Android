package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.BannerRquireContract;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerRequireRepository implements BannerRquireContract.Repository {

    private Context context;
    private int token;

    public BannerRequireRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface RequireBannerListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void requireBanner(String eventDetail, String eventPoster, String startDate, String endDate, RequireBannerListener listener) {
        Event event = new Event(eventDetail, eventPoster, startDate, endDate);
        Call<Void> call = CalendarRetrofit.getInstance().getService().requireEvent(token, event);
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
