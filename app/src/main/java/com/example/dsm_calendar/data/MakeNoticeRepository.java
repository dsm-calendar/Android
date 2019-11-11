package com.example.dsm_calendar.data;

import com.example.dsm_calendar.contract.MakeNoticeContract;
import com.example.dsm_calendar.data.DTO.Notice;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakeNoticeRepository implements MakeNoticeContract.Repository {

    public interface MakeNoticeListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void makeNotice(String title, String content, MakeNoticeListener listener) {
        Notice notice = new Notice(title, content, "", "");

        Call<Void> call = CalendarRetrofit.getInstance().getService().addNotice(notice);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    listener.onSuccess();
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else{
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
