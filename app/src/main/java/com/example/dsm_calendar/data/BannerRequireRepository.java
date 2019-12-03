package com.example.dsm_calendar.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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
        File file = new File(eventPoster);
        MultipartBody.Part detail = MultipartBody.Part.createFormData("eventDetail", eventDetail);
        MultipartBody.Part poster = MultipartBody.Part.createFormData(
                "eventPoster", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        MultipartBody.Part start = MultipartBody.Part.createFormData("startDate", startDate);
        MultipartBody.Part end = MultipartBody.Part.createFormData("endDate", endDate);
        Call<Void> call = CalendarRetrofit.getInstance().getService().requireEvent(token, detail, poster, start, end);
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
