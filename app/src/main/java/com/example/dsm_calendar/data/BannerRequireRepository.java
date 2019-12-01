package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.BannerRquireContract;
import com.example.dsm_calendar.data.DTO.Event;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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
        Map<String, RequestBody> body = new HashMap<>();
        RequestBody detail = RequestBody.create(MediaType.parse("text/plain"), eventDetail);
        body.put("eventDetail", detail);
        RequestBody poster = RequestBody.create(MediaType.parse("multipart/from-data"), eventPoster);
        body.put("eventPoster", poster);
        RequestBody start = RequestBody.create(MediaType.parse("text/plain"), startDate);
        body.put("startDate", start);
        RequestBody end = RequestBody.create(MediaType.parse("text/plain"), endDate);
        body.put("endDate", end);
        Call<Void> call = CalendarRetrofit.getInstance().getService().requireEvent(token, body);
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
