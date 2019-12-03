package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.BannerDetailContract;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerDetailRepository implements BannerDetailContract.Repository {

    private Context context;
    private int token;

    public BannerDetailRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface GetEventListener{
        void onSuccess(Event event);
        void onFail(String message);
    }

    @Override
    public void getEvent(int eventId, GetEventListener listener) {
        Call<Event> call = CalendarRetrofit.getInstance().getService().getEventDetail(token, eventId);
        call.enqueue(new Callback<Event>() {
            @Override
            public void onResponse(Call<Event> call, Response<Event> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<Event> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }
}
