package com.example.dsm_calendar.data;

import android.content.Context;

import com.example.dsm_calendar.contract.GroupScheduleContract;
import com.example.dsm_calendar.data.DTO.RoomInfo;
import com.example.dsm_calendar.data.DTO.Schedule;
import com.example.dsm_calendar.data.Singleton.CalendarRetrofit;
import com.example.dsm_calendar.data.Singleton.UserPreference;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupScheduleRepository implements GroupScheduleContract.Repository {

    private Context context;
    private int token;

    public GroupScheduleRepository(Context context) {
        this.context = context;
        this.token = UserPreference.getInstance(this.context).getUserID();
    }

    public interface GetScheduleListener{
        void onSuccess(ArrayList<Schedule> schedules);
        void onFail(String message);
    }

    public interface DeleteScheduleListener{
        void onSuccess();
        void onFail(String message);
    }

    @Override
    public void getSchedule(int roomId, GetScheduleListener listener) {
        Call<RoomInfo> call = CalendarRetrofit.getInstance().getService().getRoomInfo(token, roomId);
        call.enqueue(new Callback<RoomInfo>() {
            @Override
            public void onResponse(Call<RoomInfo> call, Response<RoomInfo> response) {
                if (response.code() == 200){
                    listener.onSuccess(response.body().getRoomSchedule());
                } else if (response.code() == 500){
                    listener.onFail("server error");
                } else {
                    listener.onFail(response.code() + "");
                }
            }

            @Override
            public void onFailure(Call<RoomInfo> call, Throwable t) {
                listener.onFail(t.getMessage());
            }
        });
    }

    @Override
    public void deleteSchedule(DeleteScheduleListener listener) {
        listener.onSuccess();
    }
}
