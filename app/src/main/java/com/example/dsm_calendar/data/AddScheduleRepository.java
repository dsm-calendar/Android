package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.AddScheduleContract;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddScheduleRepository implements AddScheduleContract.Repository {

    private Context context;
    private int token;

    public AddScheduleRepository(Context context){
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface AddScheduleListener {
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void addSchedule(String code, String title, String content, String startDay, String endDay, int groupCalendarId, AddScheduleListener lister) {
        Schedule schedule = new Schedule(title, startDay, endDay, content);
        int calendarId = -1;
        switch (code) {
            case "private":
                calendarId = UserPreference.getInstance(context).getMyCalendarID();
                break;
            case "school":
                calendarId = UserPreference.getInstance(context).getSchoolCalendarId();
                break;
            case "group":
                calendarId = groupCalendarId;
                break;
        }
        Call<Void> call = CalendarRetrofit.getInstance().getService().addSchedule(calendarId, token, schedule);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    lister.onSuccess();
                } else if(response.code() == 500){
                    lister.onFail("server error");
                } else {
                    lister.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                lister.onFail(t.getMessage());
            }
        });
    }
}
