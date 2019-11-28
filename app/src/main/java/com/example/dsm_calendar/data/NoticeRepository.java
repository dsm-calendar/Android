package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.NoticeContract;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeRepository implements NoticeContract.Repository {

    private Context context;
    private int token;

    public NoticeRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface GetNoticeListener{
        void onSuccess(ArrayList<Notice> noticeList);
        void onFail(String message);
    }

    public interface DeleteNoticeListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getNoticeList(GetNoticeListener listener) {
        Call<ArrayList<Notice>> call = CalendarRetrofit.getInstance().getService().getNoticeList(token);
        call.enqueue(new Callback<ArrayList<Notice>>() {
            @Override
            public void onResponse(Call<ArrayList<Notice>> call, Response<ArrayList<Notice>> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body());
                } else if(response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Notice>> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void deleteNotice(int noticeId, DeleteNoticeListener listener) {
        Call<Void> call = CalendarRetrofit.getInstance().getService().deleteNotice(token, noticeId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
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
